package com.education.security;

import com.alibaba.fastjson.JSON;
import com.education.config.IgnoreUrlsConfig;
import com.education.entity.RespBody;
import com.education.entity.ResultCode;
import com.education.security.filter.JwtAuthenticationTokenFilter;
import com.education.security.filter.MyFilterSecurity;
import com.education.security.handler.AuthenticationDenied;
import com.education.security.handler.AuthenticationEntry;
import com.education.security.handler.AuthenticationFailure;
import com.education.security.handler.AuthenticationSuccess;
import com.education.service.impl.AdminInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dell
 */
@Component
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminInfoServiceImpl adminInfoService;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private AuthenticationSuccess authenticationSuccess;

    @Autowired
    private AuthenticationFailure authenticationFailure;

    @Autowired
    private AuthenticationDenied authenticationDenied;

    @Autowired
    private AuthenticationEntry authenticationEntry;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MyFilterSecurity myFilterSecurity;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(adminInfoService);
        provider.setPasswordEncoder(new PasswordEncoder() {
            //密码加密
            @Override
            public String encode(CharSequence charSequence) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return passwordEncoder.encode(charSequence);
            }

            //密码匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                boolean res = passwordEncoder.matches(charSequence, s);
                return res;
            }
        });
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        /* 忽略名单添加 */
        for (String url : ignoreUrlsConfig.getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        registry.and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/education/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccess)
                .failureHandler(authenticationFailure)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(authenticationDenied)
                .authenticationEntryPoint(authenticationEntry)
                .and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myFilterSecurity, FilterSecurityInterceptor.class);
    }
}

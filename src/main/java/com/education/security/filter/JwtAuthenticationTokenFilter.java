package com.education.security.filter;

import com.education.entity.SecurityUserDetails;
import com.education.service.IRedisService;
import com.education.service.impl.AdminInfoServiceImpl;
import com.education.until.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dell
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AdminInfoServiceImpl adminInfoService;

    @Autowired
    private IRedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String username = null;
            if (authHeader.startsWith("Bearer WX_")) {
                final String authToken = authHeader.substring("Bearer WX_".length());
                username = redisService.getByKey("Authorization:" + authToken);
            } else {
                final String authToken = authHeader.substring("Bearer ".length());
                username = JwtTokenUtil.parseToken(authToken);
            }

            if (username != null) {
                UserDetails userDetails = adminInfoService.loadUserByUsername(username);
                SecurityUserDetails securityUserDetails = (SecurityUserDetails) userDetails;
                if (securityUserDetails.getRoleId() == 4L) {

                }
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

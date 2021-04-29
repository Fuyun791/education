package com.education.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author dell
 */
@Component
public class MyFilterSecurity extends AbstractSecurityInterceptor implements Filter {

  @Autowired
  private FilterInvocationSecurityMetadataSource mySecurityMetadataSource;

  @Autowired
  public void setMyAccessDecisionManager(MyAccessDecision myAccessDecisionManager) {
    super.setAccessDecisionManager(myAccessDecisionManager);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    FilterInvocation filterInvocation = new FilterInvocation(request, response, filterChain);
    InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
    try {
      filterInvocation.getChain()
          .doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
    } finally {
      super.afterInvocation(token, null);
    }
  }

  @Override
  public void destroy() {

  }

  @Override
  public Class<?> getSecureObjectClass() {
    return FilterInvocation.class;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource() {
    return this.mySecurityMetadataSource;
  }
}

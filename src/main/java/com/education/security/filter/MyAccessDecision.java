package com.education.security.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 决策是否有这个功能
 *
 * @author dell
 */
@Component
public class MyAccessDecision implements AccessDecisionManager {

  @Override
  public void decide(Authentication authentication, Object object,
      Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    // 当接口未被配置资源时直接放行
    if (configAttributes == null) {
      return;
    }
    Iterator<ConfigAttribute> iterator = configAttributes.iterator();
    while (iterator.hasNext()) {
      ConfigAttribute configAttribute = iterator.next();
      //将访问所需资源或用户拥有资源进行比对
      String needAuthority = configAttribute.getAttribute();
      for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
        if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
          return;
        }
      }
    }
    throw new AccessDeniedException("抱歉，您没有访问权限");
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}

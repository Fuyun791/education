package com.education.security.filter;

import com.education.service.IPopedomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author dell
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

  /**
   * 存放所有能访问的url
   */
  private static Map<String, ConfigAttribute> configAttributeMap = null;

  @Autowired
  private IPopedomInfoService popedomInfoService;

  @PostConstruct
  public void loadDataSource() {
    //返回ums_resource表里所有的地址访问，以单例Map的形式存在
    configAttributeMap = popedomInfoService.findPopedomInfo();
  }

  public void clearDataSource() {
    configAttributeMap.clear();
    configAttributeMap = null;
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    if (configAttributeMap == null) {
      this.loadDataSource();
    }
    List<ConfigAttribute> configAttributes = new ArrayList<>();
    //获取当前访问的路径
    String url = ((FilterInvocation) object).getRequestUrl();
    //String path = URLUtil.getPath(url);
    PathMatcher pathMatcher = new AntPathMatcher();
    Iterator<String> iterator = configAttributeMap.keySet().iterator();
    //获取访问该路径所需资源
    while (iterator.hasNext()) {
      String pattern = iterator.next();
      if (pathMatcher.match(pattern, url)) {
        configAttributes.add(configAttributeMap.get(pattern));
      }
    }
    // 未设置操作请求权限，返回空集合
    return configAttributes;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}

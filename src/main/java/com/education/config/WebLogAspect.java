package com.education.config;

import com.alibaba.fastjson.JSON;
import com.education.entity.WebLog;
import com.education.service.IWebLogService;
import com.education.until.IpInfoUtil;
import com.education.until.ObjectUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dell
 */
@Aspect
@Component
public class WebLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<Date>(
      "ThreadLocal start time");

  private final IWebLogService webLogService;

  /**
   * 通过 guava 里的 ThreadFactoryBuilder 创建线程池,采用callerRunsPolicy的拒绝策略，丢弃最老任务，重新提交，会影响性能
   */
  private static ExecutorService executor = new ThreadPoolExecutor(5, 12, 2000L,
      TimeUnit.MILLISECONDS,
      new ArrayBlockingQueue<Runnable>(100),
      new ThreadFactoryBuilder().setNameFormat("ed-pool-%d").build(),
      new ThreadPoolExecutor.CallerRunsPolicy());

  @Autowired
  public WebLogAspect(IWebLogService webLogService) {
    this.webLogService = webLogService;
  }


  @Pointcut("execution(public * com.education.controller.*.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    Date beginTime = new Date();
    BEGIN_TIME_THREAD_LOCAL.set(beginTime);
  }

  @After("webLog()")
  public void doAfter(JoinPoint joinPoint) {
    try {
      WebLog webLog = new WebLog();
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
          .getRequestAttributes();
      HttpServletRequest request = attributes.getRequest();
      //  设置方法描述
      Signature signature = joinPoint.getSignature();
      MethodSignature methodSignature = (MethodSignature) signature;
      Method method = methodSignature.getMethod();
      if (method.isAnnotationPresent(ApiOperation.class)) {
        ApiOperation log = method.getAnnotation(ApiOperation.class);
        webLog.setDescription(log.value());
      }
      Date startTime = BEGIN_TIME_THREAD_LOCAL.get();
      BEGIN_TIME_THREAD_LOCAL.remove();
      //  结束时间
      webLog.setRequestUrl(request.getRequestURL().toString());
      webLog.setRequestType(request.getMethod());
      //  设置使用方法的名称
      webLog.setRequestName(signature.getDeclaringTypeName() + "." + signature.getName());
      //  设置请求参数
      webLog.setRequestParam(ObjectUtil.mapToString(request.getParameterMap()));
      webLog.setIp(IpInfoUtil.getIpAddr(request));

      Date endTime = new Date();
      long costTime = endTime.getTime() - startTime.getTime();
      webLog.setStartTime(startTime);
      webLog.setEndTime(endTime);
      webLog.setCostTime((int) costTime);
      logger.info(JSON.toJSONString(webLog));
      executor.execute(new WebLogThread(webLog, webLogService));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 保存日志至数据库
   */
  private static class WebLogThread implements Runnable {

    private final WebLog log;
    private final IWebLogService webLogService;

    public WebLogThread(WebLog log, IWebLogService webLogService) {
      this.log = log;
      this.webLogService = webLogService;
    }

    @Override
    public void run() {
      webLogService.insertLog(log);
    }
  }

}

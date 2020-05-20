package com.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.WebLog;

import java.util.List;

/**
 * @author dell
 */
public interface IWebLogService extends IService<WebLog> {

    /**
     * 根据weblog查找日志
     * @param webLog
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<WebLog> selectLog(WebLog webLog, Integer pageStart, Integer pageSize);

    /**
     * 添加日志，给webLogAspect切面使用，将日志添加入mysql
     * @param webLog
     * @return
     */
    int insertLog(WebLog webLog);

}

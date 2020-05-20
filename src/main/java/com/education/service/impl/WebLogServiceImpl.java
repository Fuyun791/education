package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.entity.AdminInfo;
import com.education.entity.WebLog;
import com.education.mapper.WebLogMapper;
import com.education.service.IWebLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dell
 */
@Service
public class WebLogServiceImpl extends ServiceImpl<WebLogMapper, WebLog> implements IWebLogService {

    private final WebLogMapper webLogMapper;

    @Autowired
    public WebLogServiceImpl(WebLogMapper webLogMapper) {
        this.webLogMapper = webLogMapper;
    }

    @Override
    public List<WebLog> selectLog(WebLog webLog, Integer pageStart, Integer pageSize) {
        QueryWrapper<WebLog> queryWrapper=new QueryWrapper<>(webLog);
        PageHelper.startPage(pageStart,pageSize);
        return webLogMapper.selectList(queryWrapper);
    }

    @Override
    public int insertLog(WebLog webLog) {
        return webLogMapper.insert(webLog);
    }
}

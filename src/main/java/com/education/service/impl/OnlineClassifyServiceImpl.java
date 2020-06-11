package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineClassify;
import com.education.mapper.OnlineClassifyMapper;
import com.education.service.IOnlineClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-06-11
 */
@Service
public class OnlineClassifyServiceImpl extends ServiceImpl<OnlineClassifyMapper, OnlineClassify> implements IOnlineClassifyService {

    @Autowired
    private OnlineClassifyMapper onlineClassifyMapper;

    @Override
    public List<OnlineClassify> findOnlineClassify(OnlineClassify onlineClassify, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineClassify> queryWrapper=new QueryWrapper<>(onlineClassify);
        PageHelper.startPage(pageStart,pageSize);
        return onlineClassifyMapper.selectList(queryWrapper);
    }

    @Override
    public int insertOnlineClassify(OnlineClassify onlineClassify) {
        return onlineClassifyMapper.insert(onlineClassify);
    }

    @Override
    public int updateOnlineClassify(OnlineClassify onlineClassify) {
        return onlineClassifyMapper.updateById(onlineClassify);
    }

    @Override
    public int deleteOnlineClassify(int id) {
        return onlineClassifyMapper.deleteById(id);
    }

}

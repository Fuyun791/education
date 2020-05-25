package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineCourseChecked;
import com.education.mapper.OnlineCourseCheckedMapper;
import com.education.service.IOnlineCourseCheckedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 课程审核表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Service
public class OnlineCourseCheckedServiceImpl extends ServiceImpl<OnlineCourseCheckedMapper, OnlineCourseChecked> implements IOnlineCourseCheckedService {

    @Autowired
    private OnlineCourseCheckedMapper onlineCourseCheckedMapper;

    @Override
    public List<OnlineCourseChecked> findOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineCourseChecked> queryWrapper=new QueryWrapper<>(onlineCourseChecked);
        PageHelper.startPage(pageStart,pageSize);
        return onlineCourseCheckedMapper.selectList(queryWrapper);
    }

    @Override
    public int insertOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked) {
        return onlineCourseCheckedMapper.insert(onlineCourseChecked);
    }

    @Override
    public int updateOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked) {
        return onlineCourseCheckedMapper.updateById(onlineCourseChecked);
    }

    @Override
    public int deleteOnlineCourseChecked(int id) {
        return onlineCourseCheckedMapper.deleteById(id);
    }

}

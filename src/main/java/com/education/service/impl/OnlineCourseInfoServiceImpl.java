package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineCourseInfo;
import com.education.mapper.OnlineCourseInfoMapper;
import com.education.service.IOnlineCourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 在线课程表，外键暂不添加 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Service
public class OnlineCourseInfoServiceImpl extends ServiceImpl<OnlineCourseInfoMapper, OnlineCourseInfo> implements IOnlineCourseInfoService {

    @Autowired
    private OnlineCourseInfoMapper onlineCourseInfoMapper;

    @Override
    public List<OnlineCourseInfo> findOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineCourseInfo> queryWrapper=new QueryWrapper<>(onlineCourseInfo);
        PageHelper.startPage(pageStart,pageSize);
        return onlineCourseInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<OnlineCourseInfo> findOnlineCourseList(Integer teacherId, Long collegeId, Integer checkedStatus, Boolean checkedResult) {
        return onlineCourseInfoMapper.findOnlineCourseList(teacherId,collegeId,checkedStatus,checkedResult);
    }

    @Override
    public int insertOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo) {
        return onlineCourseInfoMapper.insert(onlineCourseInfo);
    }

    @Override
    public int updateOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo) {
        return onlineCourseInfoMapper.updateById(onlineCourseInfo);
    }

    @Override
    public int deleteOnlineCourseInfo(int id) {
        return onlineCourseInfoMapper.deleteById(id);
    }

}

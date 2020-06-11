package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.OnlineCourseChecked;
import com.education.mapper.OnlineCourseCheckedMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineCourseInfo;
import com.education.mapper.OnlineCourseInfoMapper;
import com.education.service.IOnlineCourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    private final OnlineCourseInfoMapper onlineCourseInfoMapper;

    private final OnlineCourseCheckedMapper onlineCourseCheckedMapper;

    @Autowired
    public OnlineCourseInfoServiceImpl(OnlineCourseInfoMapper onlineCourseInfoMapper, OnlineCourseCheckedMapper onlineCourseCheckedMapper) {
        this.onlineCourseInfoMapper = onlineCourseInfoMapper;
        this.onlineCourseCheckedMapper = onlineCourseCheckedMapper;
    }

    @Override
    public List<OnlineCourseInfo> findOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineCourseInfo> queryWrapper=new QueryWrapper<>(onlineCourseInfo);
        PageHelper.startPage(pageStart,pageSize);
        return onlineCourseInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<OnlineCourseInfo> findOnlineCourseList(Integer teacherId,Boolean isShare, Long collegeId, Integer checkedStatus, Boolean checkedResult,Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        return onlineCourseInfoMapper.findOnlineCourseList(teacherId,isShare,collegeId,checkedStatus,checkedResult);
    }

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    @Override
    public int insertOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo) {
        onlineCourseInfoMapper.insert(onlineCourseInfo);
        OnlineCourseChecked onlineCourseChecked = new OnlineCourseChecked();
        onlineCourseChecked.setOnlineCourseId(onlineCourseInfo.getId());
        onlineCourseCheckedMapper.insert(onlineCourseChecked);
        return onlineCourseCheckedMapper.insert(onlineCourseChecked);
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

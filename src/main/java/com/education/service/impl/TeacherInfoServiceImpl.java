package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.TeacherInfo;
import com.education.mapper.TeacherInfoMapper;
import com.education.service.ITeacherInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-18
 */
@Service
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements
    ITeacherInfoService {

  @Autowired
  private TeacherInfoMapper teacherInfoMapper;

  @Override
  public List<TeacherInfo> findTeacherInfo(TeacherInfo teacherInfo, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<TeacherInfo> queryWrapper = new QueryWrapper<>(teacherInfo);
    PageHelper.startPage(pageStart, pageSize);
    return teacherInfoMapper.selectList(queryWrapper);
  }

  @Override
  public List<TeacherInfo> findTeacherInfoList(TeacherInfo teacherInfo, Integer pageStart,
      Integer pageSize) {
    PageHelper.startPage(pageStart, pageSize);
    return teacherInfoMapper.findTeacherInfoList(teacherInfo);
  }

  @Override
  public int insertTeacherInfo(TeacherInfo teacherInfo) {
    return teacherInfoMapper.insert(teacherInfo);
  }

  @Override
  public int updateTeacherInfo(TeacherInfo teacherInfo) {
    return teacherInfoMapper.updateById(teacherInfo);
  }

  @Override
  public int deleteTeacherInfo(int id) {
    return teacherInfoMapper.deleteById(id);
  }

}

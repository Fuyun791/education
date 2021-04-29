package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.CourseInfo;
import com.education.mapper.CourseInfoMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.CourseName;
import com.education.mapper.CourseNameMapper;
import com.education.service.ICourseNameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Service
public class CourseNameServiceImpl extends ServiceImpl<CourseNameMapper, CourseName> implements
    ICourseNameService {

  @Autowired
  private CourseNameMapper courseNameMapper;

  @Override
  public List<CourseName> findCourseName(CourseName courseName, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<CourseName> queryWrapper = new QueryWrapper<>(courseName);
    PageHelper.startPage(pageStart, pageSize);
    return courseNameMapper.selectList(queryWrapper);
  }

  @Override
  public List<CourseName> findCourseInfoList(CourseName courseName, Integer teacherNumber,
      Integer pageStart, Integer pageSize) {
    return courseNameMapper.findCourseInfoList(courseName, teacherNumber);
  }

  @Override
  public int insertCourseName(CourseName courseName) {
    return courseNameMapper.insert(courseName);
  }

  @Override
  public int updateCourseName(CourseName courseName) {
    return courseNameMapper.updateById(courseName);
  }

  @Override
  public int deleteCourseName(int id) {
    return courseNameMapper.deleteById(id);
  }

}

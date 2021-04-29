package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineCourseHour;
import com.education.mapper.OnlineCourseHourMapper;
import com.education.service.IOnlineCourseHourService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 在线课程课时表 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Service
public class OnlineCourseHourServiceImpl extends
    ServiceImpl<OnlineCourseHourMapper, OnlineCourseHour> implements IOnlineCourseHourService {

  @Autowired
  private OnlineCourseHourMapper onlineCourseHourMapper;

  @Override
  public List<OnlineCourseHour> findOnlineCourseHour(OnlineCourseHour onlineCourseHour,
      Integer pageStart, Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<OnlineCourseHour> queryWrapper = new QueryWrapper<>(onlineCourseHour);
    PageHelper.startPage(pageStart, pageSize);
    return onlineCourseHourMapper.selectList(queryWrapper);
  }

  @Override
  public int insertOnlineCourseHour(OnlineCourseHour onlineCourseHour) {
    return onlineCourseHourMapper.insert(onlineCourseHour);
  }

  @Override
  public int updateOnlineCourseHour(OnlineCourseHour onlineCourseHour) {
    return onlineCourseHourMapper.updateById(onlineCourseHour);
  }

  @Override
  public int deleteOnlineCourseHour(int id) {
    return onlineCourseHourMapper.deleteById(id);
  }

}

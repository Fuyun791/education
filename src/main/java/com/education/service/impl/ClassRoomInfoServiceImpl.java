package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.ClassRoomInfo;
import com.education.mapper.ClassRoomInfoMapper;
import com.education.service.IClassRoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教室表 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Service
public class ClassRoomInfoServiceImpl extends
    ServiceImpl<ClassRoomInfoMapper, ClassRoomInfo> implements IClassRoomInfoService {

  @Autowired
  private ClassRoomInfoMapper classRoomInfoMapper;

  @Override
  public List<ClassRoomInfo> findClassRoomInfo(ClassRoomInfo classRoomInfo, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<ClassRoomInfo> queryWrapper = new QueryWrapper<>(classRoomInfo);
    PageHelper.startPage(pageStart, pageSize);
    return classRoomInfoMapper.selectList(queryWrapper);
  }

  @Override
  public int insertClassRoomInfo(ClassRoomInfo classRoomInfo) {
    return classRoomInfoMapper.insert(classRoomInfo);
  }

  @Override
  public int updateClassRoomInfo(ClassRoomInfo classRoomInfo) {
    return classRoomInfoMapper.updateById(classRoomInfo);
  }

  @Override
  public int deleteClassRoomInfo(int id) {
    return classRoomInfoMapper.deleteById(id);
  }

}

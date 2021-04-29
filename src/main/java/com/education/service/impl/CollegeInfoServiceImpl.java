package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.CollegeInfo;
import com.education.mapper.CollegeInfoMapper;
import com.education.service.ICollegeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class CollegeInfoServiceImpl extends ServiceImpl<CollegeInfoMapper, CollegeInfo> implements
    ICollegeInfoService {

  @Autowired
  private CollegeInfoMapper collegeInfoMapper;

  @Override
  public List<CollegeInfo> findCollegeInfo(CollegeInfo collegeInfo, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<CollegeInfo> queryWrapper = new QueryWrapper<>(collegeInfo);
    PageHelper.startPage(pageStart, pageSize);
    return collegeInfoMapper.selectList(queryWrapper);
  }

  @Override
  public int insertCollegeInfo(CollegeInfo collegeInfo) {
    return collegeInfoMapper.insert(collegeInfo);
  }

  @Override
  public int updateCollegeInfo(CollegeInfo collegeInfo) {
    return collegeInfoMapper.updateById(collegeInfo);
  }

  @Override
  public int deleteCollegeInfo(int id) {
    return collegeInfoMapper.deleteById(id);
  }

}

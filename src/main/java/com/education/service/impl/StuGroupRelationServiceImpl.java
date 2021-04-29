package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StuGroupRelation;
import com.education.mapper.StuGroupRelationMapper;
import com.education.service.IStuGroupRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-06-05
 */
@Service
public class StuGroupRelationServiceImpl extends
    ServiceImpl<StuGroupRelationMapper, StuGroupRelation> implements IStuGroupRelationService {

  @Autowired
  private StuGroupRelationMapper stuGourpRelationMapper;

  @Override
  public List<StuGroupRelation> findStuGroupRelation(StuGroupRelation stuGourpRelation,
      Integer pageStart, Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<StuGroupRelation> queryWrapper = new QueryWrapper<>(stuGourpRelation);
    PageHelper.startPage(pageStart, pageSize);
    return stuGourpRelationMapper.selectList(queryWrapper);
  }

  @Override
  public int insertStuGroupRelation(StuGroupRelation stuGourpRelation) {
    return stuGourpRelationMapper.insert(stuGourpRelation);
  }

  @Override
  public int updateStuGroupRelation(StuGroupRelation stuGourpRelation) {
    return stuGourpRelationMapper.updateById(stuGourpRelation);
  }

  @Override
  public int deleteStuGroupRelation(int id) {
    return stuGourpRelationMapper.deleteById(id);
  }

  //关注一个小组
  @Override
  public int insertMyGroup(int studentNum, int groupId, String joinTime, String dataCreate,
      String dataModified) {
    return stuGourpRelationMapper
        .insertMyGroup(studentNum, groupId, joinTime, dataCreate, dataModified);
  }

  //查询是否有关注过这个小组
  @Override
  public List<StuGroupRelation> findTheGroup(int studentNum, int groupId) {
    return stuGourpRelationMapper.findTheGroup(studentNum, groupId);
  }

}

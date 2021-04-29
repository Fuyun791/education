package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.StudentInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.GroupInfo;
import com.education.mapper.GroupInfoMapper;
import com.education.service.IGroupInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
@Service
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfo> implements
    IGroupInfoService {

  @Autowired
  private GroupInfoMapper groupInfoMapper;

  @Override
  public List<GroupInfo> findGroupInfo(GroupInfo groupInfo, Integer pageStart, Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<GroupInfo> queryWrapper = new QueryWrapper<>(groupInfo);
    PageHelper.startPage(pageStart, pageSize);
    return groupInfoMapper.selectList(queryWrapper);
  }

  @Override
  public int insertGroupInfo(GroupInfo groupInfo) {
    return groupInfoMapper.insert(groupInfo);
  }

  @Override
  public int updateGroupInfo(GroupInfo groupInfo) {
    return groupInfoMapper.updateById(groupInfo);
  }

  @Override
  public int deleteGroupInfo(int id) {
    return groupInfoMapper.deleteById(id);
  }

  //智能推荐小组列表
  @Override
  public List<StudentInfo> findAdviceGroupList(StudentInfo studentInfo) {
    return groupInfoMapper.findAdviceGroupList(studentInfo);
  }

  //我的关注小组列表
  @Override
  public List<StudentInfo> findMyGroupList(StudentInfo studentInfo) {
    return groupInfoMapper.findMyGroupList(studentInfo);
  }

  //取消关注一个小组
  @Override
  public int deleteMyGroup(int studentNum, int groupId) {
    return groupInfoMapper.deleteMyGroup(studentNum, groupId);
  }

}

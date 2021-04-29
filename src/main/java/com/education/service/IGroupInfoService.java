package com.education.service;

import com.education.entity.GroupInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.StudentInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
public interface IGroupInfoService extends IService<GroupInfo> {

  /**
   * 查找 groupInfo
   *
   * @param groupInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<GroupInfo> findGroupInfo(GroupInfo groupInfo, Integer pageStart, Integer pageSize);

  /**
   * 添加 groupInfo
   *
   * @param groupInfo
   * @return
   */
  int insertGroupInfo(GroupInfo groupInfo);

  /**
   * 修改 groupInfo
   *
   * @param groupInfo
   * @return
   */
  int updateGroupInfo(GroupInfo groupInfo);

  /**
   * 删除 groupInfo
   *
   * @param id
   * @return
   */
  int deleteGroupInfo(int id);

  //智能推荐小组列表
  List<StudentInfo> findAdviceGroupList(StudentInfo studentInfo);

  //我的关注小组列表
  List<StudentInfo> findMyGroupList(StudentInfo studentInfo);

  //取消关注一个小组
  int deleteMyGroup(int studentNum, int groupId);
}

package com.education.service;

import com.education.entity.GroupComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-06-04
 */
public interface IGroupCommentService extends IService<GroupComment> {

  /**
   * 查找 groupComment
   *
   * @param groupComment
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<GroupComment> findGroupComment(GroupComment groupComment, Integer pageStart,
      Integer pageSize);

  /**
   * 添加 groupComment
   *
   * @param groupComment
   * @return
   */
  int insertGroupComment(GroupComment groupComment);

  /**
   * 修改 groupComment
   *
   * @param groupComment
   * @return
   */
  int updateGroupComment(GroupComment groupComment);

  /**
   * 删除 groupComment
   *
   * @param id
   * @return
   */
  int deleteGroupComment(int id);

  //返回话题评论列表
  List<GroupComment> findGroupCommentList(GroupComment groupComment);

  //插入一条评论
  int insertGroupComment(int topicId, int studentNum, String commentContent, String dataModified,
      String dataCreate);

}

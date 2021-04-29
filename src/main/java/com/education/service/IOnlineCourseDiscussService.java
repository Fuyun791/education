package com.education.service;

import com.education.entity.OnlineCourseDiscuss;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
public interface IOnlineCourseDiscussService extends IService<OnlineCourseDiscuss> {

  /**
   * 查找 onlineCourseDiscuss
   *
   * @param onlineCourseDiscuss
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<OnlineCourseDiscuss> findOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss,
      Integer pageStart, Integer pageSize);

  OnlineCourseDiscuss getDiscussByCourseId(Long onlineCourseId, Long id, Integer discussPerson,
      Integer pageStart, Integer pageSize);

  /**
   * 返回在线课程的
   *
   * @param onlineCourseId
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<OnlineCourseDiscuss> findDiscussByCourseId(Long onlineCourseId, Integer pageStart,
      Integer pageSize, String model);

  /**
   * 添加 onlineCourseDiscuss
   *
   * @param onlineCourseDiscuss
   * @return
   */
  int insertOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss, Integer discussToPersonId);

  /**
   * 修改 onlineCourseDiscuss
   *
   * @param onlineCourseDiscuss
   * @return
   */
  int updateOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss);

  /**
   * 删除 onlineCourseDiscuss
   *
   * @param id
   * @return
   */
  int deleteOnlineCourseDiscuss(int id);

}

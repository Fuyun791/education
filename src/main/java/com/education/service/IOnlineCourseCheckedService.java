package com.education.service;

import com.education.entity.OnlineCourseChecked;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程审核表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
public interface IOnlineCourseCheckedService extends IService<OnlineCourseChecked> {

  /**
   * 查找 onlineCourseChecked
   *
   * @param onlineCourseChecked
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<OnlineCourseChecked> findOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked,
      Integer pageStart, Integer pageSize);

  /**
   * 添加 onlineCourseChecked
   *
   * @param onlineCourseChecked
   * @return
   */
  int insertOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked);

  /**
   * 修改 onlineCourseChecked
   *
   * @param onlineCourseChecked
   * @return
   */
  int updateOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked);

  /**
   * 删除 onlineCourseChecked
   *
   * @param id
   * @return
   */
  int deleteOnlineCourseChecked(int id);

}

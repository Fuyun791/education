package com.education.service;

import com.education.entity.CourseTimeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程时间联系表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
public interface ICourseTimeInfoService extends IService<CourseTimeInfo> {

  /**
   * 查找 courseTimeInfo
   *
   * @param courseTimeInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<CourseTimeInfo> findCourseTimeInfo(CourseTimeInfo courseTimeInfo, Integer pageStart,
      Integer pageSize);

  /**
   * 添加 courseTimeInfo
   *
   * @param courseTimeInfo
   * @return
   */
  int insertCourseTimeInfo(CourseTimeInfo courseTimeInfo);

  /**
   * 修改 courseTimeInfo
   *
   * @param courseTimeInfo
   * @return
   */
  int updateCourseTimeInfo(CourseTimeInfo courseTimeInfo);

  /**
   * 删除 courseTimeInfo
   *
   * @param id
   * @return
   */
  int deleteCourseTimeInfo(int id);

}

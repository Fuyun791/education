package com.education.service;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.CourseInfo;
import com.education.entity.CourseName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.TeacherInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
public interface ICourseNameService extends IService<CourseName> {

  /**
   * 查找 courseName
   *
   * @param courseName
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<CourseName> findCourseName(CourseName courseName, Integer pageStart, Integer pageSize);

  /**
   * 返回与课程有关的所有信息
   *
   * @param courseName
   * @return
   */
  List<CourseName> findCourseInfoList(CourseName courseName, Integer teacherNumber,
      Integer pageStart, Integer pageSize);

  /**
   * 添加 courseName
   *
   * @param courseName
   * @return
   */
  int insertCourseName(CourseName courseName);

  /**
   * 修改 courseName
   *
   * @param courseName
   * @return
   */
  int updateCourseName(CourseName courseName);

  /**
   * 删除 courseName
   *
   * @param id
   * @return
   */
  int deleteCourseName(int id);

}

package com.education.mapper;

import com.education.entity.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.StudentInfo;
import com.education.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Repository
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

  /**
   * 查询courseInfo的所有信息，包含其对应的老师，教室，时间
   *
   * @param teacherNumber
   * @param teacherName
   * @param courseName
   * @return
   */
  TeacherInfo findAllCourseInfoList(@Param("teacherNumber") Integer teacherNumber,
      @Param("teacherName") String teacherName, @Param("courseName") String courseName);

  /**
   * 返回学生的课表
   *
   * @param studentName
   * @param studentNum
   * @param courseName
   * @return
   */
  StudentInfo findStudentCourseList(@Param("studentName") String studentName,
      @Param("studentNum") Integer studentNum, @Param("courseName") String courseName);


  /*
   * 我写的
   * 返回全校课表
   */
  List<TeacherInfo> findAllCourseList();
}

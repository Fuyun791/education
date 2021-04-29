package com.education.mapper;

import com.education.entity.ClassRoomInfo;
import com.education.entity.ExamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.StudentInfo;
import com.education.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-25
 */
@Repository
public interface ExamInfoMapper extends BaseMapper<ExamInfo> {

  /**
   * 返回各学科考试安排列表
   */
  List<ExamInfo> findExamSubjectPlanList(ExamInfo examInfo);

  /*
   *返回教室考试安排列表
   */
  List<ClassRoomInfo> findExamClassRoomPlanList(ClassRoomInfo classRoomInfo);

  /**
   * 返回教师监考安排列表
   */
  List<TeacherInfo> findExamTeacherPlanList(TeacherInfo teacherInfo);

  /**
   * 返回学生考试安排列表
   */
  List<StudentInfo> findExamStudentPlanList(StudentInfo studentInfo);

}

package com.education.mapper;

import com.education.entity.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Repository
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

  /**
   * 返回学生List，包含其所属的院系，所属的班级，专业
   *
   * @param studentInfo
   * @return
   */
  List<StudentInfo> findStudentInfoList(StudentInfo studentInfo);

  StudentInfo getStudentInfo(@Param("studentNum") Integer studentNum);

}

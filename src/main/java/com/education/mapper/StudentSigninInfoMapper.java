package com.education.mapper;

import com.education.entity.StudentSigninInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 学生考勤表 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Repository
public interface StudentSigninInfoMapper extends BaseMapper<StudentSigninInfo> {

  List<StudentSigninInfo> findStudentSignin(
      @Param("studentSigninInfo") StudentSigninInfo studentSigninInfo,
      @Param("collegeId") Long collegeId,
      @Param("teacherNumber") Long teacherNumber);

}

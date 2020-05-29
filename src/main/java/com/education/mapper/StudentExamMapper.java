package com.education.mapper;

import com.education.entity.StudentExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    *  Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
@Repository
public interface StudentExamMapper extends BaseMapper<StudentExam> {


    /**
     * 返回学生考试分数列表
     */
    List<StudentInfo> findStudentExamResultList(StudentInfo studentInfo);

    /**
     * 返回学生考试分数列表(有学分)
     */
    List<StudentInfo> findStudentExamResultList2(StudentInfo studentInfo);

    //测试绩点
//    List<StudentInfo> findStudentExamResultList3(StudentInfo studentInfo);
}

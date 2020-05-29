package com.education.service;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.StudentExam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.StudentInfo;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
public interface IStudentExamService extends IService<StudentExam> {

    /**
     * 查找 studentExam
     * @param studentExam
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudentExam> findStudentExam(StudentExam studentExam, Integer pageStart, Integer pageSize);

    /**
     * 添加 studentExam
     * @param studentExam
     * @return
     */
    int insertStudentExam(StudentExam studentExam);

    /**
     * 修改 studentExam
     * @param studentExam
     * @return
     */
    int updateStudentExam(StudentExam studentExam);

    /**
     * 删除 studentExam
     * @param id
     * @return
     */
    int deleteStudentExam(int id);

    //学生考试分数列表
    List<StudentInfo> findStudentExamResultList(StudentInfo studentInfo, Integer pageStart, Integer pageSize);

    //学生考试分数列表(包括学分)
    List<StudentInfo> findStudentExamResultList2(StudentInfo studentInfo, Integer pageStart, Integer pageSize);

//    //测试绩点
////    JSONObject findStudentExamResultList3(StudentInfo studentInfo);

}

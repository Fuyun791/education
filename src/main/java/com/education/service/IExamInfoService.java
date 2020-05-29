package com.education.service;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.ClassRoomInfo;
import com.education.entity.ExamInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.StudentInfo;
import com.education.entity.TeacherInfo;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-25
 */
public interface IExamInfoService extends IService<ExamInfo> {

    /**
     * 查找 examInfo
     * @param examInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<ExamInfo> findExamInfo(ExamInfo examInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 examInfo
     * @param examInfo
     * @return
     */
    int insertExamInfo(ExamInfo examInfo);

    /**
     * 修改 examInfo
     * @param examInfo
     * @return
     */
    int updateExamInfo(ExamInfo examInfo);

    /**
     * 删除 examInfo
     * @param id
     * @return
     */
    int deleteExamInfo(int id);


    //各科目考试安排
    List<ExamInfo> findExamSubjectPlanList(ExamInfo examInfo,Integer pageStart, Integer pageSize);

    //教室考试安排列表
    List<ClassRoomInfo> findExamClassRoomPlanList(ClassRoomInfo classRoomInfo,Integer pageStart, Integer pageSize);

    //教师监考安排列表
    List<TeacherInfo> findExamTeacherPlanList(TeacherInfo teacherInfo, Integer pageStart, Integer pageSize);

    //学生考试安排列表
    List<StudentInfo> findExamStudentPlanList(StudentInfo studentInfo, Integer pageStart, Integer pageSize);
}

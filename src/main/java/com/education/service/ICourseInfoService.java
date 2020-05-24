package com.education.service;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.CourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.entity.CourseTimeInfo;
import com.education.entity.TeacherInfo;

import java.util.List;

/**
 * <p>
    * 课程表 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
public interface ICourseInfoService extends IService<CourseInfo> {

    /**
     * 查找 courseInfo
     * @param courseInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<CourseInfo> findCourseInfo(CourseInfo courseInfo, Integer pageStart, Integer pageSize);

    /**
     * 返回与课程有关的所有信息
     * @param teacherNumber
     * @param teacherName
     * @param courseName
     * @return
     */
    JSONObject findCourseInfoList(Integer teacherNumber,String teacherName,String courseName);

    /**
     * 返回学生课表
     * @param studentNum
     * @param studentName
     * @param courseName
     * @return
     */
    JSONObject findStudentCourseList(Integer studentNum, String studentName,String courseName);

    /**
     * 添加 courseInfo
     * @param courseInfo
     * @return
     */
    int insertCourseInfo(CourseInfo courseInfo);

    /**
     * 修改 courseInfo
     * @param courseInfo
     * @return
     */
    int updateCourseInfo(CourseInfo courseInfo);

    /**
     * 删除 courseInfo
     * @param id
     * @return
     */
    int deleteCourseInfo(int id);

}

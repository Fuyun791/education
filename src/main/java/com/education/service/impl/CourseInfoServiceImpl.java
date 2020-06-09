package com.education.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.StudentInfo;
import com.education.entity.TeacherInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.CourseInfo;
import com.education.mapper.CourseInfoMapper;
import com.education.service.ICourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public List<CourseInfo> findCourseInfo(CourseInfo courseInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>(courseInfo);
        PageHelper.startPage(pageStart, pageSize);
        return courseInfoMapper.selectList(queryWrapper);
    }

    @Override
    public JSONObject findCourseInfoList(Integer teacherNumber, String teacherName, String courseName) {
        TeacherInfo teacherInfo = courseInfoMapper.findAllCourseInfoList(teacherNumber,teacherName,courseName);
        String[][] strings = new String[7][12];
        if (teacherInfo == null) {
            return null;
        }
        teacherInfo.getCourseInfo().forEach(course -> {
            setCourseInfo(strings,course);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherName", teacherInfo.getTeacherName());
        jsonObject.put("teacherNumber", teacherInfo.getTeacherNumber());
        jsonObject.put("courseInfo", strings);
        return jsonObject;
    }

    @Override
    public JSONObject findStudentCourseList(Integer studentNum, String studentName, String courseName) {
        StudentInfo studentInfo = courseInfoMapper.findStudentCourseList(studentName,studentNum,courseName);
        String[][] strings = new String[7][12];
        if (studentInfo == null) {
            return null;
        }
        studentInfo.getCourseInfo().forEach(course -> {
            setCourseInfo(strings,course);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentName", studentInfo.getStudentName());
        jsonObject.put("studentNum", studentInfo.getStudentNum());
        jsonObject.put("courseInfo", strings);
        return jsonObject;
    }

    private void setCourseInfo(String[][] strings,CourseInfo course) {
        course.getCourseTimeInfo().forEach(time -> {
            StringBuilder stringBuilder = new StringBuilder();
            String[] split = time.getDayly().split("\\|");
            stringBuilder.append(course.getCourseName().getCourseName());
            stringBuilder.append("\n");
            stringBuilder.append(course.getClassRoomInfo().getRoomNumber());
            stringBuilder.append("\n");
            stringBuilder.append(course.getCourseTimeInfo().get(0).getWeekly());
            int index = getDay(split[0]);
            for (int i = 1; i < split.length; i++) {
                strings[index][Integer.parseInt(split[i])] = stringBuilder.toString();
            }
        });
    }

    private int getDay(String string) {
        switch (string) {
            case "周一":
                return 0;
            case "周二":
                return 1;
            case "周三":
                return 2;
            case "周四":
                return 3;
            case "周五":
                return 4;
            case "周六":
                return 5;
            case "周日":
                return 6;
            default:
                return -1;
        }
    }

    @Override
    public int insertCourseInfo(CourseInfo courseInfo) {
        return courseInfoMapper.insert(courseInfo);
    }

    @Override
    public int updateCourseInfo(CourseInfo courseInfo) {
        return courseInfoMapper.updateById(courseInfo);
    }

    @Override
    public int deleteCourseInfo(int id) {
        return courseInfoMapper.deleteById(id);
    }


    //我写的返回全校课表
    @Override
    public List<TeacherInfo> findAllCourseList(Integer pageStart, Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        return courseInfoMapper.findAllCourseList();
    }

}

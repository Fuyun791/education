package com.education.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.ClassRoomInfo;
import com.education.entity.StudentInfo;
import com.education.entity.TeacherInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.ExamInfo;
import com.education.mapper.ExamInfoMapper;
import com.education.service.IExamInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-25
 */
@Service
public class ExamInfoServiceImpl extends ServiceImpl<ExamInfoMapper, ExamInfo> implements IExamInfoService {

    @Autowired
    private ExamInfoMapper examInfoMapper;

    @Override
    public List<ExamInfo> findExamInfo(ExamInfo examInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<ExamInfo> queryWrapper=new QueryWrapper<>(examInfo);
        PageHelper.startPage(pageStart,pageSize);
        return examInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int insertExamInfo(ExamInfo examInfo) {
        return examInfoMapper.insert(examInfo);
    }

    @Override
    public int updateExamInfo(ExamInfo examInfo) {
        return examInfoMapper.updateById(examInfo);
    }

    @Override
    public int deleteExamInfo(int id) {
        return examInfoMapper.deleteById(id);
    }


    //各科目考试安排
    @Override
        public List<ExamInfo> findExamSubjectPlanList(ExamInfo examInfo,Integer pageStart, Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        return examInfoMapper.findExamSubjectPlanList(examInfo);
    }

    //教室考试安排列表
    @Override
        public List<ClassRoomInfo> findExamClassRoomPlanList(ClassRoomInfo classRoomInfo,Integer pageStart, Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        return examInfoMapper.findExamClassRoomPlanList(classRoomInfo);
    }

    //教师监考安排列表
    @Override
        public List<TeacherInfo> findExamTeacherPlanList(TeacherInfo teacherInfo, Integer pageStart, Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        return examInfoMapper.findExamTeacherPlanList(teacherInfo);
    }

    //学生考试安排列表
    @Override
        public List<StudentInfo> findExamStudentPlanList(StudentInfo studentInfo, Integer pageStart, Integer pageSize){
        PageHelper.startPage(pageStart,pageSize);
        return examInfoMapper.findExamStudentPlanList(studentInfo);
    }
}

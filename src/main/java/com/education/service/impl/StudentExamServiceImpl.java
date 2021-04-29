package com.education.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.StudentInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StudentExam;
import com.education.mapper.StudentExamMapper;
import com.education.service.IStudentExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
@Service
public class StudentExamServiceImpl extends ServiceImpl<StudentExamMapper, StudentExam> implements
    IStudentExamService {

  @Autowired
  private StudentExamMapper studentExamMapper;

  @Override
  public List<StudentExam> findStudentExam(StudentExam studentExam, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<StudentExam> queryWrapper = new QueryWrapper<>(studentExam);
    PageHelper.startPage(pageStart, pageSize);
    return studentExamMapper.selectList(queryWrapper);
  }

  @Override
  public int insertStudentExam(StudentExam studentExam) {
    return studentExamMapper.insert(studentExam);
  }

  @Override
  public int updateStudentExam(StudentExam studentExam) {
    return studentExamMapper.updateById(studentExam);
  }

  @Override
  public int deleteStudentExam(int id) {
    return studentExamMapper.deleteById(id);
  }

  //学生考试分数列表
  @Override
  public List<StudentInfo> findStudentExamResultList(StudentInfo studentInfo, Integer pageStart,
      Integer pageSize) {
    PageHelper.startPage(pageStart, pageSize);
    return studentExamMapper.findStudentExamResultList(studentInfo);
  }

  //学生考试分数列表(包括学分)
  @Override
  public List<StudentInfo> findStudentExamResultList2(StudentInfo studentInfo, Integer pageStart,
      Integer pageSize) {
    PageHelper.startPage(pageStart, pageSize);
    return studentExamMapper.findStudentExamResultList2(studentInfo);
  }

  //测试绩点
//    @Override
//    public JSONObject findStudentExamResultList3(StudentInfo studentInfo, Integer pageStart, Integer pageSize){
//        StudentExam studentExam = studentExamMapper.findStudentExamResultList3(studentInfo);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("examPoint",studentExam.getScore()/10 - 5);
//        return jsonObject;
//    }


}

package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StudentSigninInfo;
import com.education.mapper.StudentSigninInfoMapper;
import com.education.service.IStudentSigninInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 学生考勤表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Service
public class StudentSigninInfoServiceImpl extends ServiceImpl<StudentSigninInfoMapper, StudentSigninInfo> implements IStudentSigninInfoService {

    @Autowired
    private StudentSigninInfoMapper studentSigninInfoMapper;

    @Override
    public List<StudentSigninInfo> findStudentSigninInfo(StudentSigninInfo studentSigninInfo, Long collegeId, Long teacherNumber, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        return studentSigninInfoMapper.findStudentSignin(studentSigninInfo, collegeId, teacherNumber);
    }

    @Override
    public int insertStudentSigninInfo(StudentSigninInfo studentSigninInfo) {
        return studentSigninInfoMapper.insert(studentSigninInfo);
    }

    @Override
    public int updateStudentSigninInfo(StudentSigninInfo studentSigninInfo) {
        return studentSigninInfoMapper.updateById(studentSigninInfo);
    }

    @Override
    public int deleteStudentSigninInfo(int id) {
        return studentSigninInfoMapper.deleteById(id);
    }

}

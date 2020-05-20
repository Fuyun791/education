package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StudentInfo;
import com.education.mapper.StudentInfoMapper;
import com.education.service.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public List<StudentInfo> findStudentInfo(StudentInfo studentInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<StudentInfo> queryWrapper=new QueryWrapper<>(studentInfo);
        PageHelper.startPage(pageStart,pageSize);
        return studentInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<StudentInfo> findStudentInfoList(StudentInfo studentInfo, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        return studentInfoMapper.findStudentInfoList(studentInfo);
    }

    @Override
    public int insertStudentInfo(StudentInfo studentInfo) {
        return studentInfoMapper.insert(studentInfo);
    }

    @Override
    public int updateStudentInfo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public int deleteStudentInfo(int id) {
        return studentInfoMapper.deleteById(id);
    }

}

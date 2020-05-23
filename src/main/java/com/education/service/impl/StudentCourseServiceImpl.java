package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StudentCourse;
import com.education.mapper.StudentCourseMapper;
import com.education.service.IStudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 学生与课程的连接 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements IStudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public List<StudentCourse> findStudentCourse(StudentCourse studentCourse, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<StudentCourse> queryWrapper=new QueryWrapper<>(studentCourse);
        PageHelper.startPage(pageStart,pageSize);
        return studentCourseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertStudentCourse(StudentCourse studentCourse) {
        return studentCourseMapper.insert(studentCourse);
    }

    @Override
    public int updateStudentCourse(StudentCourse studentCourse) {
        return studentCourseMapper.updateById(studentCourse);
    }

    @Override
    public int deleteStudentCourse(int id) {
        return studentCourseMapper.deleteById(id);
    }

}

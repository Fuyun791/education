package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>(courseInfo);
        PageHelper.startPage(pageStart,pageSize);
        return courseInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<TeacherInfo> findCourseInfoList(CourseInfo courseInfo, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        return courseInfoMapper.findAllCourseInfoList(courseInfo);
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

}

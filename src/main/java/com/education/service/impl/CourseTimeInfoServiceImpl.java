package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.CourseTimeInfo;
import com.education.mapper.CourseTimeInfoMapper;
import com.education.service.ICourseTimeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 课程时间联系表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Service
public class CourseTimeInfoServiceImpl extends ServiceImpl<CourseTimeInfoMapper, CourseTimeInfo> implements ICourseTimeInfoService {

    @Autowired
    private CourseTimeInfoMapper courseTimeInfoMapper;

    @Override
    public List<CourseTimeInfo> findCourseTimeInfo(CourseTimeInfo courseTimeInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<CourseTimeInfo> queryWrapper=new QueryWrapper<>(courseTimeInfo);
        PageHelper.startPage(pageStart,pageSize);
        return courseTimeInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int insertCourseTimeInfo(CourseTimeInfo courseTimeInfo) {
        return courseTimeInfoMapper.insert(courseTimeInfo);
    }

    @Override
    public int updateCourseTimeInfo(CourseTimeInfo courseTimeInfo) {
        return courseTimeInfoMapper.updateById(courseTimeInfo);
    }

    @Override
    public int deleteCourseTimeInfo(int id) {
        return courseTimeInfoMapper.deleteById(id);
    }

}

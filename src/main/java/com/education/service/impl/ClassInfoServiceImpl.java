package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.mapper.MyClassInfoMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.ClassInfo;
import com.education.service.IClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 班级表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<MyClassInfoMapper, ClassInfo> implements IClassInfoService {

    @Autowired
    private MyClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> findClassInfo(ClassInfo classInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<ClassInfo> queryWrapper=new QueryWrapper<>(classInfo);
        PageHelper.startPage(pageStart,pageSize);
        return classInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<ClassInfo> findClassInfoList(ClassInfo classInfo, Integer collegeId, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        return classInfoMapper.findClassInfoList(classInfo,collegeId);
    }

    @Override
    public int insertClassInfo(ClassInfo classInfo) {
        return classInfoMapper.insert(classInfo);
    }

    @Override
    public int updateClassInfo(ClassInfo classInfo) {
        return classInfoMapper.updateById(classInfo);
    }

    @Override
    public int deleteClassInfo(int id) {
        return classInfoMapper.deleteById(id);
    }

}

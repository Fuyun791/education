package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.FacultyInfo;
import com.education.mapper.FacultyInfoMapper;
import com.education.service.IFacultyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 院系表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class FacultyInfoServiceImpl extends ServiceImpl<FacultyInfoMapper, FacultyInfo> implements IFacultyInfoService {

    @Autowired
    private FacultyInfoMapper facultyInfoMapper;

    @Override
    public List<FacultyInfo> findFacultyInfo(FacultyInfo facultyInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<FacultyInfo> queryWrapper=new QueryWrapper<>(facultyInfo);
        PageHelper.startPage(pageStart,pageSize);
        return facultyInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int insertFacultyInfo(FacultyInfo facultyInfo) {
        return facultyInfoMapper.insert(facultyInfo);
    }

    @Override
    public int updateFacultyInfo(FacultyInfo facultyInfo) {
        return facultyInfoMapper.updateById(facultyInfo);
    }

    @Override
    public int deleteFacultyInfo(int id) {
        return facultyInfoMapper.deleteById(id);
    }

}

package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.SpecialtyInfo;
import com.education.mapper.SpecialtyInfoMapper;
import com.education.service.ISpecialtyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 专业表 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class SpecialtyInfoServiceImpl extends ServiceImpl<SpecialtyInfoMapper, SpecialtyInfo> implements ISpecialtyInfoService {

    @Autowired
    private SpecialtyInfoMapper specialtyInfoMapper;

    @Override
    public List<SpecialtyInfo> findSpecialtyInfo(SpecialtyInfo specialtyInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<SpecialtyInfo> queryWrapper=new QueryWrapper<>(specialtyInfo);
        PageHelper.startPage(pageStart,pageSize);
        return specialtyInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<SpecialtyInfo> findSpecialtyInfoList(SpecialtyInfo specialtyInfo, Integer collegeId, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart,pageSize);
        return specialtyInfoMapper.findSpecialtyInfoList(specialtyInfo,collegeId);
    }

    @Override
    public int insertSpecialtyInfo(SpecialtyInfo specialtyInfo) {
        return specialtyInfoMapper.insert(specialtyInfo);
    }

    @Override
    public int updateSpecialtyInfo(SpecialtyInfo specialtyInfo) {
        return specialtyInfoMapper.updateById(specialtyInfo);
    }

    @Override
    public int deleteSpecialtyInfo(int id) {
        return specialtyInfoMapper.deleteById(id);
    }

}

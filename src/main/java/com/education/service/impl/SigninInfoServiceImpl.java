package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.SigninInfo;
import com.education.mapper.SigninInfoMapper;
import com.education.service.ISigninInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Service
public class SigninInfoServiceImpl extends ServiceImpl<SigninInfoMapper, SigninInfo> implements ISigninInfoService {

    private final SigninInfoMapper signinInfoMapper;

    @Autowired
    public SigninInfoServiceImpl(SigninInfoMapper signinInfoMapper) {
        this.signinInfoMapper = signinInfoMapper;
    }

    @Override
    public List<SigninInfo> findSigninInfo(SigninInfo signinInfo, Long collegeId, Long teacherNumber, Integer pageStart, Integer pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        return signinInfoMapper.selectSigninAndCourseName(signinInfo, collegeId, teacherNumber);
    }

    @Override
    public int insertSigninInfo(SigninInfo signinInfo) {
        return signinInfoMapper.insert(signinInfo);
    }

    @Override
    public int updateSigninInfo(SigninInfo signinInfo) {
        return signinInfoMapper.updateById(signinInfo);
    }

    @Override
    public int deleteSigninInfo(int id) {
        return signinInfoMapper.deleteById(id);
    }

}

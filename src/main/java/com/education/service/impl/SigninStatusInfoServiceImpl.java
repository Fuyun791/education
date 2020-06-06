package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.SigninStatusInfo;
import com.education.mapper.SigninStatusInfoMapper;
import com.education.service.ISigninStatusInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Service
public class SigninStatusInfoServiceImpl extends ServiceImpl<SigninStatusInfoMapper, SigninStatusInfo> implements ISigninStatusInfoService {

    @Autowired
    private SigninStatusInfoMapper signinStatusInfoMapper;

    @Override
    public List<SigninStatusInfo> findSigninStatusInfo(SigninStatusInfo signinStatusInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<SigninStatusInfo> queryWrapper=new QueryWrapper<>(signinStatusInfo);
        PageHelper.startPage(pageStart,pageSize);
        return signinStatusInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int insertSigninStatusInfo(SigninStatusInfo signinStatusInfo) {
        return signinStatusInfoMapper.insert(signinStatusInfo);
    }

    @Override
    public int updateSigninStatusInfo(SigninStatusInfo signinStatusInfo) {
        return signinStatusInfoMapper.updateById(signinStatusInfo);
    }

    @Override
    public int deleteSigninStatusInfo(int id) {
        return signinStatusInfoMapper.deleteById(id);
    }

}

package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.RolePopedom;
import com.education.mapper.RolePopedomMapper;
import com.education.service.IRolePopedomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-11
 */
@Service
public class RolePopedomServiceImpl extends ServiceImpl<RolePopedomMapper, RolePopedom> implements IRolePopedomService {

    @Autowired
    private RolePopedomMapper rolePopedomMapper;

    @Override
    public List<RolePopedom> findRolePopedom(RolePopedom rolePopedom, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<RolePopedom> queryWrapper=new QueryWrapper<>(rolePopedom);
        PageHelper.startPage(pageStart,pageSize);
        return rolePopedomMapper.selectList(queryWrapper);
    }

    @Override
    public int insertRolePopedom(RolePopedom rolePopedom) {
        return rolePopedomMapper.insert(rolePopedom);
    }

    @Override
    public int updateRolePopedom(RolePopedom rolePopedom) {
        return rolePopedomMapper.updateById(rolePopedom);
    }

    @Override
    public int deleteRolePopedom(int id) {
        return rolePopedomMapper.deleteById(id);
    }

}

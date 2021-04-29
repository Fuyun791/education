package com.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.service.IPopedomInfoService;
import com.education.service.IRedisService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.education.entity.RoleInfo;
import com.education.mapper.RoleInfoMapper;
import com.education.service.IRoleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements
    IRoleInfoService {

  @Autowired
  private RoleInfoMapper roleInfoMapper;

  @Autowired
  private IPopedomInfoService popedomInfoService;

  @Autowired
  private IRedisService redisService;

  @Override
  public List<RoleInfo> findRoleInfo(RoleInfo roleInfo, Integer pageStart, Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>(roleInfo);
    PageHelper.startPage(pageStart, pageSize);
    return roleInfoMapper.selectList(queryWrapper);
  }

  @Override
  public RoleInfo findRoleAndPopedomInfoById(Long roleId) {
    RoleInfo roleInfo = null;
    String str = "roleInfo:" + roleId;
    if (redisService.getByKey(str) == null) {
      roleInfo = roleInfoMapper.selectById(roleId);
      roleInfo.setPopedomInfoList(popedomInfoService.findPopedomMenuByRoleId(roleId));
      redisService.set(str, JSON.toJSONString(roleInfo), 11L, TimeUnit.HOURS);
    } else {
      roleInfo = JSON.parseObject(redisService.getByKey(str), RoleInfo.class);
    }
    return roleInfo;
  }

  @Override
  public int insertRoleInfo(RoleInfo roleInfo) {
    return roleInfoMapper.insert(roleInfo);
  }

  @Override
  public int updateRoleInfo(RoleInfo roleInfo) {
    return roleInfoMapper.updateById(roleInfo);
  }

  @Override
  public int deleteRoleInfo(int id) {
    return roleInfoMapper.deleteById(id);
  }

}

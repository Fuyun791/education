package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.education.entity.PopedomInfo;
import com.education.mapper.PopedomInfoMapper;
import com.education.service.IPopedomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 访问权限表 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Service
public class PopedomInfoServiceImpl extends ServiceImpl<PopedomInfoMapper, PopedomInfo> implements
    IPopedomInfoService {

  @Autowired
  private PopedomInfoMapper popedomInfoMapper;

  @Override
  public List<PopedomInfo> findPopedomInfo(PopedomInfo popedomInfo, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<PopedomInfo> queryWrapper = new QueryWrapper<>(popedomInfo);
    PageHelper.startPage(pageStart, pageSize);
    return popedomInfoMapper.selectList(queryWrapper);
  }

  @Override
  public List<PopedomInfo> findPopedomMenuByRoleId(Long roleId) {
    List<PopedomInfo> popedomInfoList = popedomInfoMapper.findPopedomList(roleId, 0L);
    for (PopedomInfo popedomInfo : popedomInfoList) {
      if (popedomInfo.getPopedomChild() != 0) {
        List<PopedomInfo> popedomList = popedomInfoMapper
            .findPopedomList(roleId, popedomInfo.getId());
        popedomInfo.setPopedomInfoList(popedomList);
      }
    }
    return popedomInfoList;
  }

  @Override
  public Map<String, ConfigAttribute> findPopedomInfo() {
    Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
    List<PopedomInfo> popedomInfoList = popedomInfoMapper
        .selectList(new QueryWrapper<PopedomInfo>());
    for (PopedomInfo popedomInfo : popedomInfoList) {
      if (!StringUtils.isEmpty(popedomInfo.getPopedomUrl())) {
        map.put(popedomInfo.getPopedomUrl(), new SecurityConfig(popedomInfo.getPopedomName()));
      }
    }
    return map;
  }

  @Override
  public int insertPopedomInfo(PopedomInfo popedomInfo) {
    return popedomInfoMapper.insert(popedomInfo);
  }

  @Override
  public int updatePopedomInfo(PopedomInfo popedomInfo) {
    return popedomInfoMapper.updateById(popedomInfo);
  }

  @Override
  public int deletePopedomInfo(int id) {
    return popedomInfoMapper.deleteById(id);
  }

}

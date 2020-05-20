package com.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.PopedomInfo;
import com.education.entity.SecurityUserDetails;
import com.education.mapper.RoleInfoMapper;
import com.education.service.IRedisService;
import com.education.service.IRoleInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.education.entity.AdminInfo;
import com.education.mapper.AdminInfoMapper;
import com.education.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 主要分主管理员和子管理员 服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService, UserDetailsService {

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Autowired
    private IRoleInfoService roleInfoService;

    @Autowired
    private IRedisService redisService;

    @Override
    public List<AdminInfo> findAdminInfo(AdminInfo adminInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<AdminInfo> queryWrapper=new QueryWrapper<>(adminInfo);
        PageHelper.startPage(pageStart,pageSize);
        return adminInfoMapper.selectList(queryWrapper);
    }

    @Override
    public AdminInfo findAdminAndPopedomInfoById(Long adminId) {
        String str = "adminInfo:" + adminId;
        AdminInfo adminInfo = null;
        if (redisService.getByKey(str) == null) {
            adminInfo = adminInfoMapper.selectOne(new QueryWrapper<AdminInfo>().eq("admin_number",adminId));
            adminInfo.setRoleInfo(roleInfoService.findRoleAndPopedomInfoById(adminInfo.getRoleId()));
            redisService.set(str, JSON.toJSONString(adminInfo), 10L, TimeUnit.HOURS);
        } else {
            adminInfo = JSON.parseObject(redisService.getByKey(str),AdminInfo.class);
        }
        return adminInfo;
    }

    @Override
    public int insertAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.insert(adminInfo);
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.updateById(adminInfo);
    }

    @Override
    public int deleteAdminInfo(int id) {
        return adminInfoMapper.deleteById(id);
    }

    /**
     * security 加载用户信息
     * @param adminId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        AdminInfo adminInfo = adminInfoMapper.selectOne(new QueryWrapper<AdminInfo>().eq("admin_number",adminId));
        if (adminInfo != null) {
            List<PopedomInfo> popedomInfoList = null;
            String str = "popedomInfo:" + adminInfo.getRoleId();
            if (redisService.getByKey(str) == null) {
                popedomInfoList = adminInfoMapper.getPopedomInfoListByRoleId(adminInfo.getRoleId());
                redisService.set(str, JSON.toJSONString(popedomInfoList), 10L, TimeUnit.HOURS);
            } else {
                popedomInfoList = JSON.parseArray(redisService.getByKey(str),PopedomInfo.class);
            }
            return new SecurityUserDetails(adminInfo,popedomInfoList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}

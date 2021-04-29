package com.education.service;

import com.education.entity.AdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 主要分主管理员和子管理员 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
public interface IAdminInfoService extends IService<AdminInfo> {

  /**
   * 查找 adminInfo
   *
   * @param adminInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<AdminInfo> findAdminInfo(AdminInfo adminInfo, Integer pageStart, Integer pageSize);

  /**
   * 通过id找到用户，同时获得其的菜单列表
   *
   * @param adminId
   * @return
   */
  AdminInfo findAdminAndPopedomInfoById(Long adminId);

  /**
   * 添加 adminInfo
   *
   * @param adminInfo
   * @return
   */
  int insertAdminInfo(AdminInfo adminInfo);

  /**
   * 修改 adminInfo
   *
   * @param adminInfo
   * @return
   */
  int updateAdminInfo(AdminInfo adminInfo);

  /**
   * 删除 adminInfo
   *
   * @param id
   * @return
   */
  int deleteAdminInfo(int id);

}

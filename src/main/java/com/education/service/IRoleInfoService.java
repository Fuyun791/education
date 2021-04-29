package com.education.service;

import com.education.entity.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
public interface IRoleInfoService extends IService<RoleInfo> {

  /**
   * 查找 roleInfo
   *
   * @param roleInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<RoleInfo> findRoleInfo(RoleInfo roleInfo, Integer pageStart, Integer pageSize);

  /**
   * 通过id找到角色权限所对应的菜单目录
   *
   * @param roleId
   * @return
   */
  RoleInfo findRoleAndPopedomInfoById(Long roleId);

  /**
   * 添加 roleInfo
   *
   * @param roleInfo
   * @return
   */
  int insertRoleInfo(RoleInfo roleInfo);

  /**
   * 修改 roleInfo
   *
   * @param roleInfo
   * @return
   */
  int updateRoleInfo(RoleInfo roleInfo);

  /**
   * 删除 roleInfo
   *
   * @param id
   * @return
   */
  int deleteRoleInfo(int id);

}

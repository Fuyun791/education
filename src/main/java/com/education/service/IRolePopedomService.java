package com.education.service;

import com.education.entity.RolePopedom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-11
 */
public interface IRolePopedomService extends IService<RolePopedom> {

  /**
   * 查找 rolePopedom
   *
   * @param rolePopedom
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<RolePopedom> findRolePopedom(RolePopedom rolePopedom, Integer pageStart, Integer pageSize);

  /**
   * 添加 rolePopedom
   *
   * @param rolePopedom
   * @return
   */
  int insertRolePopedom(RolePopedom rolePopedom);

  /**
   * 修改 rolePopedom
   *
   * @param rolePopedom
   * @return
   */
  int updateRolePopedom(RolePopedom rolePopedom);

  /**
   * 删除 rolePopedom
   *
   * @param id
   * @return
   */
  int deleteRolePopedom(int id);

}

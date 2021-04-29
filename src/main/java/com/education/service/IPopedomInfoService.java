package com.education.service;

import com.education.entity.PopedomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.access.ConfigAttribute;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访问权限表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
public interface IPopedomInfoService extends IService<PopedomInfo> {

  /**
   * 查找 popedomInfo
   *
   * @param popedomInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<PopedomInfo> findPopedomInfo(PopedomInfo popedomInfo, Integer pageStart, Integer pageSize);

  /**
   * 通过roleId返回其所对应的角色目录
   *
   * @param roleId
   * @return
   */
  List<PopedomInfo> findPopedomMenuByRoleId(Long roleId);

  /**
   * 找出全部可访问的资源url
   *
   * @return
   */
  Map<String, ConfigAttribute> findPopedomInfo();

  /**
   * 添加 popedomInfo
   *
   * @param popedomInfo
   * @return
   */
  int insertPopedomInfo(PopedomInfo popedomInfo);

  /**
   * 修改 popedomInfo
   *
   * @param popedomInfo
   * @return
   */
  int updatePopedomInfo(PopedomInfo popedomInfo);

  /**
   * 删除 popedomInfo
   *
   * @param id
   * @return
   */
  int deletePopedomInfo(int id);

}

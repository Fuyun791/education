package com.education.mapper;

import com.education.entity.AdminInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.PopedomInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 主要分主管理员和子管理员 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Repository
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {

  /**
   * 查找这个角色所能访问的功能
   *
   * @param roleId
   * @return
   */
  List<PopedomInfo> getPopedomInfoListByRoleId(@Param("roleId") Long roleId);

}

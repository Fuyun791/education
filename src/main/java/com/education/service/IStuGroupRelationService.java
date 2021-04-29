package com.education.service;

import com.education.entity.StuGroupRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-06-05
 */
public interface IStuGroupRelationService extends IService<StuGroupRelation> {

  /**
   * 查找 stuGourpRelation
   *
   * @param stuGourpRelation
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<StuGroupRelation> findStuGroupRelation(StuGroupRelation stuGourpRelation, Integer pageStart,
      Integer pageSize);

  /**
   * 添加 stuGourpRelation
   *
   * @param stuGourpRelation
   * @return
   */
  int insertStuGroupRelation(StuGroupRelation stuGourpRelation);

  /**
   * 修改 stuGourpRelation
   *
   * @param stuGourpRelation
   * @return
   */
  int updateStuGroupRelation(StuGroupRelation stuGourpRelation);

  /**
   * 删除 stuGourpRelation
   *
   * @param id
   * @return
   */
  int deleteStuGroupRelation(int id);

  //关注一个小组
  int insertMyGroup(int studentNum, int groupId, String joinTime, String dataCreate,
      String dataModified);

  //查询是否有关注过这个小组
  List<StuGroupRelation> findTheGroup(int studentNum, int groupId);

}

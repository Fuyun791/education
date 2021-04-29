package com.education.service;

import com.education.entity.SpecialtyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专业表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
public interface ISpecialtyInfoService extends IService<SpecialtyInfo> {

  /**
   * 查找 specialtyInfo
   *
   * @param specialtyInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<SpecialtyInfo> findSpecialtyInfo(SpecialtyInfo specialtyInfo, Integer pageStart,
      Integer pageSize);

  /**
   * 查找 specialtyInfoList根据学校返回其限定的学院与专业
   *
   * @param specialtyInfo
   * @param collegeId
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<SpecialtyInfo> findSpecialtyInfoList(SpecialtyInfo specialtyInfo, Integer collegeId,
      Integer pageStart, Integer pageSize);

  /**
   * 添加 specialtyInfo
   *
   * @param specialtyInfo
   * @return
   */
  int insertSpecialtyInfo(SpecialtyInfo specialtyInfo);

  /**
   * 修改 specialtyInfo
   *
   * @param specialtyInfo
   * @return
   */
  int updateSpecialtyInfo(SpecialtyInfo specialtyInfo);

  /**
   * 删除 specialtyInfo
   *
   * @param id
   * @return
   */
  int deleteSpecialtyInfo(int id);

}

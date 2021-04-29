package com.education.service;

import com.education.entity.FacultyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 院系表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
public interface IFacultyInfoService extends IService<FacultyInfo> {

  /**
   * 查找 facultyInfo
   *
   * @param facultyInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<FacultyInfo> findFacultyInfo(FacultyInfo facultyInfo, Integer pageStart, Integer pageSize);

  /**
   * 添加 facultyInfo
   *
   * @param facultyInfo
   * @return
   */
  int insertFacultyInfo(FacultyInfo facultyInfo);

  /**
   * 修改 facultyInfo
   *
   * @param facultyInfo
   * @return
   */
  int updateFacultyInfo(FacultyInfo facultyInfo);

  /**
   * 删除 facultyInfo
   *
   * @param id
   * @return
   */
  int deleteFacultyInfo(int id);

}

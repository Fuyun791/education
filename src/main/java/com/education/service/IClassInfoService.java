package com.education.service;

import com.education.entity.ClassInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
public interface IClassInfoService extends IService<ClassInfo> {

  /**
   * 查找 classInfo
   *
   * @param classInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<ClassInfo> findClassInfo(ClassInfo classInfo, Integer pageStart, Integer pageSize);

  /**
   * 返回classInfoList包含其院校
   *
   * @param classInfo
   * @param collegeId
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<ClassInfo> findClassInfoList(ClassInfo classInfo, Integer collegeId, Integer pageStart,
      Integer pageSize);

  /**
   * 添加 classInfo
   *
   * @param classInfo
   * @return
   */
  int insertClassInfo(ClassInfo classInfo);

  /**
   * 修改 classInfo
   *
   * @param classInfo
   * @return
   */
  int updateClassInfo(ClassInfo classInfo);

  /**
   * 删除 classInfo
   *
   * @param id
   * @return
   */
  int deleteClassInfo(int id);

}

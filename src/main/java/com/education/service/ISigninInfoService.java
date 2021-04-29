package com.education.service;

import com.education.entity.SigninInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
public interface ISigninInfoService extends IService<SigninInfo> {

  /**
   * 查找 signinInfo
   *
   * @param signinInfo
   * @param collegeId
   * @param teacherNumber
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<SigninInfo> findSigninInfo(SigninInfo signinInfo, Long collegeId, Long teacherNumber,
      Integer pageStart, Integer pageSize);

  /**
   * 添加 signinInfo
   *
   * @param signinInfo
   * @return
   */
  int insertSigninInfo(SigninInfo signinInfo);

  /**
   * 修改 signinInfo
   *
   * @param signinInfo
   * @return
   */
  int updateSigninInfo(SigninInfo signinInfo);

  /**
   * 删除 signinInfo
   *
   * @param id
   * @return
   */
  int deleteSigninInfo(int id);

}

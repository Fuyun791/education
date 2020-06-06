package com.education.service;

import com.education.entity.SigninStatusInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
public interface ISigninStatusInfoService extends IService<SigninStatusInfo> {

    /**
     * 查找 signinStatusInfo
     * @param signinStatusInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<SigninStatusInfo> findSigninStatusInfo(SigninStatusInfo signinStatusInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 signinStatusInfo
     * @param signinStatusInfo
     * @return
     */
    int insertSigninStatusInfo(SigninStatusInfo signinStatusInfo);

    /**
     * 修改 signinStatusInfo
     * @param signinStatusInfo
     * @return
     */
    int updateSigninStatusInfo(SigninStatusInfo signinStatusInfo);

    /**
     * 删除 signinStatusInfo
     * @param id
     * @return
     */
    int deleteSigninStatusInfo(int id);

}

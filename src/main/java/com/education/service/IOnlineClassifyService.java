package com.education.service;

import com.education.entity.OnlineClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-06-11
 */
public interface IOnlineClassifyService extends IService<OnlineClassify> {

    /**
     * 查找 onlineClassify
     * @param onlineClassify
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<OnlineClassify> findOnlineClassify(OnlineClassify onlineClassify, Integer pageStart, Integer pageSize);

    /**
     * 添加 onlineClassify
     * @param onlineClassify
     * @return
     */
    int insertOnlineClassify(OnlineClassify onlineClassify);

    /**
     * 修改 onlineClassify
     * @param onlineClassify
     * @return
     */
    int updateOnlineClassify(OnlineClassify onlineClassify);

    /**
     * 删除 onlineClassify
     * @param id
     * @return
     */
    int deleteOnlineClassify(int id);

}

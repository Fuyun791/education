package com.education.service;

import com.education.entity.OnlineCourseStar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-06-08
 */
public interface IOnlineCourseStarService extends IService<OnlineCourseStar> {

    /**
     * 查找 onlineCourseStar
     * @param onlineCourseStar
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<OnlineCourseStar> findOnlineCourseStar(OnlineCourseStar onlineCourseStar, Integer pageStart, Integer pageSize);

    /**
     * 添加 onlineCourseStar
     * @param onlineCourseStar
     * @return
     */
    int insertOnlineCourseStar(OnlineCourseStar onlineCourseStar,Long discussParent,  Integer discussPerson, Long onlineCourseId,Integer discussToPerson) throws Exception;

    //还要写个获取star的代码
    Set<String> getOnlineCourseStar(String discussPerson, Long onlineCourseId);

    /**
     * 修改 onlineCourseStar
     * @param onlineCourseStar
     * @return
     */
    int updateOnlineCourseStar(OnlineCourseStar onlineCourseStar);

    /**
     * 删除 onlineCourseStar
     * @param id
     * @return
     */
    int deleteOnlineCourseStar(int id);

}

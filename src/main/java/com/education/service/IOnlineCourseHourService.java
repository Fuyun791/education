package com.education.service;

import com.education.entity.OnlineCourseHour;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    * 在线课程课时表 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
public interface IOnlineCourseHourService extends IService<OnlineCourseHour> {

    /**
     * 查找 onlineCourseHour
     * @param onlineCourseHour
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<OnlineCourseHour> findOnlineCourseHour(OnlineCourseHour onlineCourseHour, Integer pageStart, Integer pageSize);

    /**
     * 添加 onlineCourseHour
     * @param onlineCourseHour
     * @return
     */
    int insertOnlineCourseHour(OnlineCourseHour onlineCourseHour);

    /**
     * 修改 onlineCourseHour
     * @param onlineCourseHour
     * @return
     */
    int updateOnlineCourseHour(OnlineCourseHour onlineCourseHour);

    /**
     * 删除 onlineCourseHour
     * @param id
     * @return
     */
    int deleteOnlineCourseHour(int id);

}

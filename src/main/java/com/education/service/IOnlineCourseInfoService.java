package com.education.service;

import com.education.entity.OnlineCourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
    * 在线课程表，外键暂不添加 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
public interface IOnlineCourseInfoService extends IService<OnlineCourseInfo> {

    /**
     * 查找 onlineCourseInfo
     * @param onlineCourseInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<OnlineCourseInfo> findOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo, Integer pageStart, Integer pageSize);

    /**
     * 根据状态返回相应的在线课程
     * @param teacherId
     * @param collegeId
     * @param checkedStatus
     * @param checkedResult
     * @return
     */
    List<OnlineCourseInfo> findOnlineCourseList(Integer teacherId,Boolean isShare, Long collegeId, Integer checkedStatus, Boolean checkedResult,Integer pageStart, Integer pageSize);

    /**
     * 添加 onlineCourseInfo
     * @param onlineCourseInfo
     * @return
     */
    int insertOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo);

    /**
     * 修改 onlineCourseInfo
     * @param onlineCourseInfo
     * @return
     */
    int updateOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo);

    /**
     * 删除 onlineCourseInfo
     * @param id
     * @return
     */
    int deleteOnlineCourseInfo(int id);

}

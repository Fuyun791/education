package com.education.service;

import com.education.entity.CollegeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
public interface ICollegeInfoService extends IService<CollegeInfo> {

    /**
     * 查找 collegeInfo
     * @param collegeInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<CollegeInfo> findCollegeInfo(CollegeInfo collegeInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 collegeInfo
     * @param collegeInfo
     * @return
     */
    int insertCollegeInfo(CollegeInfo collegeInfo);

    /**
     * 修改 collegeInfo
     * @param collegeInfo
     * @return
     */
    int updateCollegeInfo(CollegeInfo collegeInfo);

    /**
     * 删除 collegeInfo
     * @param id
     * @return
     */
    int deleteCollegeInfo(int id);

}

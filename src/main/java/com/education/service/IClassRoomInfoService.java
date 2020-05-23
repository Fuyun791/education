package com.education.service;

import com.education.entity.ClassRoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    * 教室表 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
public interface IClassRoomInfoService extends IService<ClassRoomInfo> {

    /**
     * 查找 classRoomInfo
     * @param classRoomInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<ClassRoomInfo> findClassRoomInfo(ClassRoomInfo classRoomInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 classRoomInfo
     * @param classRoomInfo
     * @return
     */
    int insertClassRoomInfo(ClassRoomInfo classRoomInfo);

    /**
     * 修改 classRoomInfo
     * @param classRoomInfo
     * @return
     */
    int updateClassRoomInfo(ClassRoomInfo classRoomInfo);

    /**
     * 删除 classRoomInfo
     * @param id
     * @return
     */
    int deleteClassRoomInfo(int id);

}

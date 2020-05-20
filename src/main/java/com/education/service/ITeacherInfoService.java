package com.education.service;

import com.education.entity.TeacherInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-18
 */
public interface ITeacherInfoService extends IService<TeacherInfo> {

    /**
     * 查找 teacherInfo
     * @param teacherInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<TeacherInfo> findTeacherInfo(TeacherInfo teacherInfo, Integer pageStart, Integer pageSize);

    /**
     * 查找教师信息，包含院校
     * @param teacherInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<TeacherInfo> findTeacherInfoList(TeacherInfo teacherInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 teacherInfo
     * @param teacherInfo
     * @return
     */
    int insertTeacherInfo(TeacherInfo teacherInfo);

    /**
     * 修改 teacherInfo
     * @param teacherInfo
     * @return
     */
    int updateTeacherInfo(TeacherInfo teacherInfo);

    /**
     * 删除 teacherInfo
     * @param id
     * @return
     */
    int deleteTeacherInfo(int id);

}

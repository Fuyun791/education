package com.education.service;

import com.education.entity.AdminInfo;
import com.education.entity.StudentInfo;
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
public interface IStudentInfoService extends IService<StudentInfo> {

    /**
     * 查找 studentInfo
     * @param studentInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudentInfo> findStudentInfo(StudentInfo studentInfo, Integer pageStart, Integer pageSize);

    /**
     * 根据studentInfo的条件返回学生信息，包含学校，专业
     * @param studentInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudentInfo> findStudentInfoList(StudentInfo studentInfo, Integer pageStart, Integer pageSize);

    /**
     * 添加 studentInfo
     * @param studentInfo
     * @return
     */
    int insertStudentInfo(StudentInfo studentInfo);

    /**
     * 修改 studentInfo
     * @param studentInfo
     * @return
     */
    int updateStudentInfo(StudentInfo studentInfo);

    /**
     * 删除 studentInfo
     * @param id
     * @return
     */
    int deleteStudentInfo(int id);

    int updateStudentPic(AdminInfo adminInfo);
}

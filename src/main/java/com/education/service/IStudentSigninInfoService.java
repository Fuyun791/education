package com.education.service;

import com.education.entity.StudentSigninInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    * 学生考勤表 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
public interface IStudentSigninInfoService extends IService<StudentSigninInfo> {

    /**
     * 查找 studentSigninInfo
     * @param studentSigninInfo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudentSigninInfo> findStudentSigninInfo(StudentSigninInfo studentSigninInfo, Long collegeId, Long teacherNumber, Integer pageStart, Integer pageSize);

    /**
     * 添加 studentSigninInfo
     * @param studentSigninInfo
     * @return
     */
    int insertStudentSigninInfo(StudentSigninInfo studentSigninInfo);

    /**
     * 修改 studentSigninInfo
     * @param studentSigninInfo
     * @return
     */
    int updateStudentSigninInfo(StudentSigninInfo studentSigninInfo);

    /**
     * 删除 studentSigninInfo
     * @param id
     * @return
     */
    int deleteStudentSigninInfo(int id);

}

package com.education.service;

import com.education.entity.StudentCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
    * 学生与课程的连接 服务类
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
public interface IStudentCourseService extends IService<StudentCourse> {

    /**
     * 查找 studentCourse
     * @param studentCourse
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudentCourse> findStudentCourse(StudentCourse studentCourse, Integer pageStart, Integer pageSize);

    /**
     * 添加 studentCourse
     * @param studentCourse
     * @return
     */
    int insertStudentCourse(StudentCourse studentCourse);

    /**
     * 修改 studentCourse
     * @param studentCourse
     * @return
     */
    int updateStudentCourse(StudentCourse studentCourse);

    /**
     * 删除 studentCourse
     * @param id
     * @return
     */
    int deleteStudentCourse(int id);

}

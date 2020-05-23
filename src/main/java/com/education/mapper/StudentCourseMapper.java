package com.education.mapper;

import com.education.entity.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
    * 学生与课程的连接 Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

}

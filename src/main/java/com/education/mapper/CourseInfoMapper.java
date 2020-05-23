package com.education.mapper;

import com.education.entity.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.TeacherInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    * 课程表 Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Repository
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    /**
     * 查询courseInfo的所有信息，包含其对应的老师，教室，时间
     * @param courseInfo
     * @return
     */
    List<TeacherInfo> findAllCourseInfoList(CourseInfo courseInfo);

}

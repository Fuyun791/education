package com.education.mapper;

import com.education.entity.CourseInfo;
import com.education.entity.CourseName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Repository
public interface CourseNameMapper extends BaseMapper<CourseName> {

  /**
   * 在这里进行一个查询，参照courseInfo
   *
   * @param course
   * @return
   */
  List<CourseName> findCourseInfoList(@Param("course") CourseName course,
      @Param("teacherNumber") Integer teacherNumber);

}

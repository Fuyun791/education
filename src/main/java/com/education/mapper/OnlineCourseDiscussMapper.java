package com.education.mapper;

import com.education.entity.OnlineCourseDiscuss;
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
 * @since 2020-05-28
 */
@Repository
public interface OnlineCourseDiscussMapper extends BaseMapper<OnlineCourseDiscuss> {

  /**
   * 根据课程号返回讨论区
   *
   * @param onlineCourseId
   * @param discussParent
   * @return
   */
  List<OnlineCourseDiscuss> findDiscussByCourseId(@Param("onlineCourseId") Long onlineCourseId,
      @Param("discussParent") Long discussParent);

  int isExist(@Param("adminNumber") Integer adminNumber, @Param("adminName") String adminName);

}

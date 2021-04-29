package com.education.mapper;

import com.education.entity.OnlineCourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 在线课程表，外键暂不添加 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Repository
public interface OnlineCourseInfoMapper extends BaseMapper<OnlineCourseInfo> {

  /**
   * 返回在线课程的相应状态
   *
   * @param teacherId
   * @param isShare
   * @param collegeId
   * @param checkedStatus
   * @param checkedResult
   * @return
   */
  List<OnlineCourseInfo> findOnlineCourseList(@Param("teacherId") Integer teacherId,
      @Param("isShare") Boolean isShare,
      @Param("collegeId") Long collegeId,
      @Param("checkedStatus") Integer checkedStatus, @Param("checkedResult") Boolean checkedResult);

}

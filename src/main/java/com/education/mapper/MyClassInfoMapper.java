package com.education.mapper;

import com.education.entity.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.SpecialtyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 班级表 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Repository
public interface MyClassInfoMapper extends BaseMapper<ClassInfo> {

  /**
   * 查找classInfo包含其院系
   *
   * @param classInfo
   * @param collegeId
   * @return
   */
  List<ClassInfo> findClassInfoList(@Param("classInfo") ClassInfo classInfo,
      @Param("collegeId") Integer collegeId);

}

package com.education.mapper;

import com.education.entity.SpecialtyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    * 专业表 Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Repository
public interface SpecialtyInfoMapper extends BaseMapper<SpecialtyInfo> {

    /**
     * 查找专业包含其所对应的院系
     * @param specialtyInfo
     * @param collegeId
     * @return
     */
    List<SpecialtyInfo> findSpecialtyInfoList(@Param("specialty") SpecialtyInfo specialtyInfo, @Param("collegeId") Integer collegeId);

}

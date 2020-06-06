package com.education.mapper;

import com.education.entity.SigninInfo;
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
 * @since 2020-05-31
 */
@Repository
public interface SigninInfoMapper extends BaseMapper<SigninInfo> {

    /**
     * 查询signinList和课程名称
     * @param signinInfo
     * @param collegeId
     * @param teacherNumber
     * @return
     */
    List<SigninInfo> selectSigninAndCourseName(@Param("signin") SigninInfo signinInfo,
                                               @Param("collegeId") Long collegeId,
                                               @Param("teacherNumber") Long teacherNumber);

}

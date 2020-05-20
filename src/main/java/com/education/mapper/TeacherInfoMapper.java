package com.education.mapper;

import com.education.entity.TeacherInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    *  Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-18
 */
@Repository
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {

    /**
     * 返回teacher列表，包含学校与院系
     * @param teacherInfo
     * @return
     */
    List<TeacherInfo> findTeacherInfoList(TeacherInfo teacherInfo);

}

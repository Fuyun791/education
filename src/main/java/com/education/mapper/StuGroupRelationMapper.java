package com.education.mapper;

import com.education.entity.StuGroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * <p>
    *  Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-06-05
 */
@Repository
public interface StuGroupRelationMapper extends BaseMapper<StuGroupRelation> {

    //关注一个小组
    int insertMyGroup(@Param("studentNum") int studentNum,
                      @Param("groupId") int groupId,
                      @Param("joinTime") String joinTime,
                      @Param("dataCreate") String dataCreate,
                      @Param("dataModified") String dataModified);

    // 查询是否有关注过这个小组
    List<StuGroupRelation> findTheGroup(@Param("studentNum") int studentNum,@Param("groupId") int groupId);
}

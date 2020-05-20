package com.education.mapper;

import com.education.entity.PopedomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    * 访问权限表 Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Repository
public interface PopedomInfoMapper extends BaseMapper<PopedomInfo> {

    /**
     * 输出当前层次的目录
     * @param roleId
     * @param popedomParent
     * @return
     */
    List<PopedomInfo> findPopedomList(@Param("roleId") Long roleId, @Param("popedomParent") Long popedomParent);

}

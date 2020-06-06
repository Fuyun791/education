package com.education.mapper;

import com.education.entity.OnlineEpisodes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    *  Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Repository
public interface OnlineEpisodesMapper extends BaseMapper<OnlineEpisodes> {

    /**
     * 根据在线课程Id返回其章节
     * @param onlineCourseId
     * @return
     */
    List<OnlineEpisodes> findEpisodesByCourseId(@Param("onlineCourseId")Long onlineCourseId,@Param("collegeId")Long collegeId);

}

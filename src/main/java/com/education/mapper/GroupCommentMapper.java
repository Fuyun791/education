package com.education.mapper;

import com.education.entity.GroupComment;
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
 * @since 2020-06-04
 */
@Repository
public interface GroupCommentMapper extends BaseMapper<GroupComment> {

  //返回话题评论列表
  List<GroupComment> findGroupCommentList(GroupComment groupComment);

  //插入一条评论
  int insertGroupComment(@Param("topicId") int topicId,
      @Param("studentNum") int studentNum,
      @Param("commentContent") String commentContent,
      @Param("dataModified") String dataModified,
      @Param("dataCreate") String dataCreate
  );
}

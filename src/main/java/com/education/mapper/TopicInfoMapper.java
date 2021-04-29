package com.education.mapper;

import com.education.entity.TopicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
@Repository
public interface TopicInfoMapper extends BaseMapper<TopicInfo> {

  //返回文章题目、简介、图片列表
  List<TopicInfo> findTopicInfoList(TopicInfo topicInfo);

  //返回文章题目、内容列表
  List<TopicInfo> findTopicContentList(TopicInfo topicInfo);
}

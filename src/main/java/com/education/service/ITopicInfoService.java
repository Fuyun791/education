package com.education.service;

import com.education.entity.TopicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.redis.listener.Topic;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
public interface ITopicInfoService extends IService<TopicInfo> {

  /**
   * 查找 topicInfo
   *
   * @param topicInfo
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<TopicInfo> findTopicInfo(TopicInfo topicInfo, Integer pageStart, Integer pageSize);

  /**
   * 添加 topicInfo
   *
   * @param topicInfo
   * @return
   */
  int insertTopicInfo(TopicInfo topicInfo);

  /**
   * 修改 topicInfo
   *
   * @param topicInfo
   * @return
   */
  int updateTopicInfo(TopicInfo topicInfo);

  /**
   * 删除 topicInfo
   *
   * @param id
   * @return
   */
  int deleteTopicInfo(int id);

  //返回文章题目、简介、图片列表
  List<TopicInfo> findTopicInfoList(TopicInfo topicInfo);

  //返回文章题目、内容列表
  List<TopicInfo> findTopicContentList(TopicInfo topicInfo);
}

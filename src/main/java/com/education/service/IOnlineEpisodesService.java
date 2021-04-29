package com.education.service;

import com.education.entity.OnlineEpisodes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
public interface IOnlineEpisodesService extends IService<OnlineEpisodes> {

  /**
   * 查找 onlineEpisodes
   *
   * @param onlineEpisodes
   * @param pageStart
   * @param pageSize
   * @return
   */
  List<OnlineEpisodes> findOnlineEpisodes(OnlineEpisodes onlineEpisodes, Integer pageStart,
      Integer pageSize);

  /**
   * 根据在线课程Id返回其章节
   *
   * @param onlineCourseId
   * @return
   */
  List<OnlineEpisodes> findEpisodesByCourseId(Long onlineCourseId, Long collegeId);

  /**
   * 添加 onlineEpisodes
   *
   * @param onlineEpisodes
   * @return
   */
  int insertOnlineEpisodes(OnlineEpisodes onlineEpisodes);

  /**
   * 修改 onlineEpisodes
   *
   * @param onlineEpisodes
   * @return
   */
  int updateOnlineEpisodes(OnlineEpisodes onlineEpisodes);

  /**
   * 删除 onlineEpisodes
   *
   * @param id
   * @return
   */
  int deleteOnlineEpisodes(int id);

}

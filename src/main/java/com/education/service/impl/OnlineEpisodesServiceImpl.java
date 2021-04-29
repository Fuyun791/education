package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.OnlineEpisodes;
import com.education.mapper.OnlineEpisodesMapper;
import com.education.service.IOnlineEpisodesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Service
public class OnlineEpisodesServiceImpl extends
    ServiceImpl<OnlineEpisodesMapper, OnlineEpisodes> implements IOnlineEpisodesService {

  @Autowired
  private OnlineEpisodesMapper onlineEpisodesMapper;

  @Override
  public List<OnlineEpisodes> findOnlineEpisodes(OnlineEpisodes onlineEpisodes, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<OnlineEpisodes> queryWrapper = new QueryWrapper<>(onlineEpisodes);
    PageHelper.startPage(pageStart, pageSize);
    return onlineEpisodesMapper.selectList(queryWrapper);
  }

  @Override
  public List<OnlineEpisodes> findEpisodesByCourseId(Long onlineCourseId, Long collegeId) {
    return onlineEpisodesMapper.findEpisodesByCourseId(onlineCourseId, collegeId);
  }

  @Override
  public int insertOnlineEpisodes(OnlineEpisodes onlineEpisodes) {
    return onlineEpisodesMapper.insert(onlineEpisodes);
  }

  @Override
  public int updateOnlineEpisodes(OnlineEpisodes onlineEpisodes) {
    return onlineEpisodesMapper.updateById(onlineEpisodes);
  }

  @Override
  public int deleteOnlineEpisodes(int id) {
    return onlineEpisodesMapper.deleteById(id);
  }

}

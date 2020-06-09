package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.TopicInfo;
import com.education.mapper.TopicInfoMapper;
import com.education.service.ITopicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
@Service
public class TopicInfoServiceImpl extends ServiceImpl<TopicInfoMapper, TopicInfo> implements ITopicInfoService {

    @Autowired
    private TopicInfoMapper topicInfoMapper;

    @Override
    public List<TopicInfo> findTopicInfo(TopicInfo topicInfo, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<TopicInfo> queryWrapper=new QueryWrapper<>(topicInfo);
        PageHelper.startPage(pageStart,pageSize);
        return topicInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTopicInfo(TopicInfo topicInfo) {
        return topicInfoMapper.insert(topicInfo);
    }

    @Override
    public int updateTopicInfo(TopicInfo topicInfo) {
        return topicInfoMapper.updateById(topicInfo);
    }

    @Override
    public int deleteTopicInfo(int id) {
        return topicInfoMapper.deleteById(id);
    }

    //文章题目、简介、图片列表
    @Override
    public List<TopicInfo> findTopicInfoList(TopicInfo topicInfo){
        return topicInfoMapper.findTopicInfoList(topicInfo);
    }

    //文章题目、内容列表
    @Override
    public List<TopicInfo> findTopicContentList(TopicInfo topicInfo){
        return topicInfoMapper.findTopicContentList(topicInfo);
    }
}

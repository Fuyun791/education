package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.GroupComment;
import com.education.mapper.GroupCommentMapper;
import com.education.service.IGroupCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author dell
 * @since 2020-06-04
 */
@Service
public class GroupCommentServiceImpl extends ServiceImpl<GroupCommentMapper, GroupComment> implements IGroupCommentService {

    @Autowired
    private GroupCommentMapper groupCommentMapper;

    @Override
    public List<GroupComment> findGroupComment(GroupComment groupComment, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<GroupComment> queryWrapper=new QueryWrapper<>(groupComment);
        PageHelper.startPage(pageStart,pageSize);
        return groupCommentMapper.selectList(queryWrapper);
    }

    @Override
    public int insertGroupComment(GroupComment groupComment) {
        return groupCommentMapper.insert(groupComment);
    }

    @Override
    public int updateGroupComment(GroupComment groupComment) {
        return groupCommentMapper.updateById(groupComment);
    }

    @Override
    public int deleteGroupComment(int id) {
        return groupCommentMapper.deleteById(id);
    }

    //返回话题评论列表
    @Override
    public List<GroupComment> findGroupCommentList(GroupComment groupComment){
        return groupCommentMapper.findGroupCommentList(groupComment);
    }

    @Override
    public int insertGroupComment (int topicId,int studentNum,String commentContent,String dataModified,String dataCreate){
        return groupCommentMapper.insertGroupComment(topicId,studentNum,commentContent,dataModified,dataCreate);
    }
}

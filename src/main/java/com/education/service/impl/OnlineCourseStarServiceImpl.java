package com.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.OnlineCourseDiscuss;
import com.education.service.IRedisService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.education.entity.OnlineCourseStar;
import com.education.mapper.OnlineCourseStarMapper;
import com.education.service.IOnlineCourseStarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-06-08
 */
@Service
public class OnlineCourseStarServiceImpl extends ServiceImpl<OnlineCourseStarMapper, OnlineCourseStar> implements IOnlineCourseStarService {

    private final OnlineCourseStarMapper onlineCourseStarMapper;

    private final IRedisService redisService;

    @Autowired
    public OnlineCourseStarServiceImpl(OnlineCourseStarMapper onlineCourseStarMapper, IRedisService redisService) {
        this.onlineCourseStarMapper = onlineCourseStarMapper;
        this.redisService = redisService;
    }

    @Override
    public List<OnlineCourseStar> findOnlineCourseStar(OnlineCourseStar onlineCourseStar, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineCourseStar> queryWrapper = new QueryWrapper<>(onlineCourseStar);
        PageHelper.startPage(pageStart, pageSize);
        return onlineCourseStarMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    @Override
    public int insertOnlineCourseStar(OnlineCourseStar onlineCourseStar, Long discussParent, Integer discussPerson, Long onlineCourseId) {
        //这个key代表的意思是课程号下的评论id
        int result = 0;
        String discussKey = "onlineCourseDiscuss:" + onlineCourseId;
        String discussStarKey = "onlineCourseDiscussStar:" + onlineCourseId;
        if (discussParent != 0) {
            String temp = ":" + discussParent + "_" + discussPerson;
            discussKey += temp;
            discussStarKey += temp;
        }
        //课程号+id
        String hash = onlineCourseId + "_" + onlineCourseStar.getDiscussCourseId();
        String keyStar = "discussStar:" + onlineCourseStar.getStarPerson();
        //代表要添加对象的KeyHash
        String hashStarKey = onlineCourseStar.getDiscussCourseId() + "_" + discussPerson;
        //存在说明应该做删除操作
        if (redisService.rank(keyStar, hash) != null) {
            //对onlineCourse里的star做减法
            OnlineCourseDiscuss onlineCourseDiscuss = JSON.parseObject(String.valueOf(redisService.getHashValue(discussKey,hashStarKey)), OnlineCourseDiscuss.class);
            redisService.putHash(discussKey,hashStarKey,JSON.toJSONString(onlineCourseDiscuss.incrementStar(-1)));
            //降低onlineCourseStar里的排序
            redisService.incrementScore(discussStarKey, hashStarKey, -1);
            //移除discussStar里的评论
            redisService.remove(keyStar, hash);
            //删除sql记录
            QueryWrapper<OnlineCourseStar> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("discuss_course_id", onlineCourseStar.getDiscussCourseId())
                    .eq("star_person", onlineCourseStar.getStarPerson());
            result = onlineCourseStarMapper.delete(queryWrapper);
        } else {
            //添加sql和在discussStar这个用户下添加此评论
            onlineCourseStar.setDataCreate(LocalDateTime.now());
            onlineCourseStar.setDataModified(LocalDateTime.now());
            result = onlineCourseStarMapper.insert(onlineCourseStar);
            redisService.addSetSort(keyStar, hash,
                    onlineCourseStar.getDataModified().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            //修改onlineCourse里的star数量
            OnlineCourseDiscuss onlineCourseDiscuss = JSON.parseObject(String.valueOf(redisService.getHashValue(discussKey,hashStarKey)), OnlineCourseDiscuss.class);
            redisService.putHash(discussKey,hashStarKey,JSON.toJSONString(onlineCourseDiscuss.incrementStar(1)));
            //增加onlineCourseStar里的star排序得分
            redisService.incrementScore(discussStarKey, hashStarKey, 1);
        }
        return result;
    }

    @Override
    public Set<String> getOnlineCourseStar(String discussPerson, Long onlineCourseId) {
        String key = "discussStar:" + discussPerson;
        String con = String.valueOf(onlineCourseId);
        Set<String> starSet = redisService.reverseRange(key, 0, -1);
        Set<String> result = new HashSet<>();
        starSet.forEach(star -> {
            if (star.contains(con)) {
                result.add(star);
            }
        });
        return result;
    }

    @Override
    public int updateOnlineCourseStar(OnlineCourseStar onlineCourseStar) {
        return onlineCourseStarMapper.updateById(onlineCourseStar);
    }

    @Override
    public int deleteOnlineCourseStar(int id) {
        return onlineCourseStarMapper.deleteById(id);
    }

}

package com.education.mapper;

import com.education.entity.GroupInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.entity.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
    *  Mapper 接口
    * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
@Repository
public interface GroupInfoMapper extends BaseMapper<GroupInfo> {

    //返回智能推荐小组列表
    List<StudentInfo> findAdviceGroupList(StudentInfo studentInfo);

    //返回我的关注小组列表
    List<StudentInfo> findMyGroupList (StudentInfo studentInfo);

    //取消关注一个小组
    int deleteMyGroup(@Param("studentNum") int studentNum,@Param("groupId") int groupId);
}

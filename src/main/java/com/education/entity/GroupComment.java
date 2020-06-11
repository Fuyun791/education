package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dell
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GroupComment对象", description="")
public class GroupComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键(topic_info表)")
    private Long topicId;

    @ApiModelProperty(value = "外键(student_info表)")
    private Long studentId;

    @ApiModelProperty(value = "所属评论(子评论)")
    private Long activityId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "评论头像图片")
    private String commentImg;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    //为了显示学生姓名和管理员头像加的
    private String studentName;

    private String adminPic;

}

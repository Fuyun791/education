package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 在线课程表，外键暂不添加
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OnlineCourseInfo对象", description="在线课程表，外键暂不添加")
public class OnlineCourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程简介，不超过200字")
    private String onlineBrief;

    @ApiModelProperty(value = "外键课程分类表online_classify", example = "0")
    private Long onlineType;

    @ApiModelProperty(value = "授课教师，外键teacher_info", example = "0")
    private Long teacherId;

    @TableField(exist = false)
    private String teacherName;

    @ApiModelProperty(value = "学时", example = "0")
    private Integer time;

    @ApiModelProperty(value = "授课的学校,外键course_info", example = "0")
    private Long collegeId;

    @ApiModelProperty(value = "0未开课，1已开课，2已结课", example = "0")
    private Integer isWork;

    private String onelinePic;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "推荐权重", example = "0")
    private Integer weight;

    @ApiModelProperty(value = "课程是否共享给其他院校,0不分享，1分享")
    private Boolean isShare;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataModified;

    @TableField(exist = false)
    private OnlineCourseChecked onlineCourseChecked;


}

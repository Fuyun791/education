package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OnlineCourseDiscuss对象", description="")
public class OnlineCourseDiscuss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键online_course_info", example = "0")
    private Long onlineCourseId;

    @ApiModelProperty(value = "评论内容")
    private String discussText;

    @ApiModelProperty(value = "0代表一级，不是0代表他的父评论", example = "0")
    private Long discussParent;

    private Boolean discussChild = false;

    @ApiModelProperty(value = "评论人，用admin_info里的id", example = "0")
    private Integer discussPerson;

    @TableField(exist = false)
    private String discussPersonName;

    @ApiModelProperty(value = "回复中，作为@的对象", example = "0")
    private Integer discussToPerson;

    @TableField(exist = false)
    private String discussToPersonName;

    @TableField(exist = false)
    private String discussPersonPic;

    @ApiModelProperty(value = "点赞数，只有一级有", example = "0")
    private Long star;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataModified;

    @TableField(exist = false)
    private List<OnlineCourseDiscuss> onlineCourseDiscussList;


}

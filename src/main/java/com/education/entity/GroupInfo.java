package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GroupInfo对象", description="")
public class GroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "小组简介")
    private String groupBrief;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "小组头像图片")
    private String groupImg;

    @ApiModelProperty(value = "外键(院校表college_info)")
    private Long collegeId;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    //为了多表操作加的
    @TableField(exist = false)
    private TopicInfo topicInfo;


}

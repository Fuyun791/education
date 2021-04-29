package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 *
 * </p>
 *
 * @author dell
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TopicInfo对象", description = "")
public class TopicInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "外键(兴趣小组表group_info)", example = "0")
  private Long groupId;

  @ApiModelProperty(value = "话题名称")
  private String topicName;

  @ApiModelProperty(value = "话题简介")
  private String topicBrief;

  @ApiModelProperty(value = "话题内容")
  private String topicContent;

  @ApiModelProperty(value = "发布人", example = "0")
  private Long userId;

  @ApiModelProperty(value = "话题图片")
  private String topicImg;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dataCreate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dataModified;

  //为了查小组名称
  private String groupName;

}

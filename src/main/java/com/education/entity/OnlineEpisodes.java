package com.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

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
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OnlineEpisodes对象", description = "")
public class OnlineEpisodes implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "在线课程号", example = "0")
  private Long onlineCourseId;

  @ApiModelProperty(value = "例章节1：网络层")
  private String episodesName;

  private Integer sort;

  @ApiModelProperty(value = "章节简介")
  private String episodesBrief;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private List<OnlineCourseHour> onlineCourseHourList;


}

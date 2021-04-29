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
 * 课程时间联系表
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseTimeInfo对象", description = "课程时间联系表")
public class CourseTimeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "外键(course_info)", example = "0")
  private Long courseId;

  @ApiModelProperty(value = "第几周到第几周")
  private String weekly;

  @ApiModelProperty(value = "周几上课")
  private String dayly;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;


}

package com.education.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SigninInfo对象", description = "")
public class SigninInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @ApiModelProperty(value = "外键(课程表course_info)", example = "0")
  private Long courseId;

  @ApiModelProperty(value = "允许考勤区域")
  private LocalDateTime startTime;

  private LocalDateTime finishTime;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private String courseName;


}

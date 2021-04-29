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
 * @since 2020-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SigninStatusInfo对象", description = "")
public class SigninStatusInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "对应的考勤日期")
  private LocalDateTime data;

  @ApiModelProperty(value = "外键(课表course_info)", example = "0")
  private Long courseId;

  @ApiModelProperty(value = "出勤人数", example = "0")
  private Integer attendance;

  @ApiModelProperty(value = "迟到人数", example = "0")
  private Integer late;

  @ApiModelProperty(value = "旷课人数", example = "0")
  private Integer out;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;


}

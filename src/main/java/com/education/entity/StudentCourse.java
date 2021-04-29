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
 * 学生与课程的连接
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentCourse对象", description = "学生与课程的连接")
public class StudentCourse implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "外键(课程表course_info)", example = "0")
  private Long courseId;

  @ApiModelProperty(value = "外键(学生表student_info)", example = "0")
  private Integer studentNum;

  @ApiModelProperty(value = "成绩", example = "0")
  private Integer score;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;


}

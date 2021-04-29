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
 * 学生考勤表
 * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentSigninInfo对象", description = "学生考勤表")
public class StudentSigninInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "外键(学生表student_info)", example = "0")
  private Long studentId;

  @ApiModelProperty(value = "外键(考勤表signin_info)", example = "0")
  private Long signinId;

  private LocalDateTime signTime;

  @ApiModelProperty(value = "0为没考勤，1为考勤", example = "0")
  private Integer status;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @ApiModelProperty(value = "status=2的总数", example = "0")
  @TableField(exist = false)
  private Integer signinAttendance;

  @ApiModelProperty(value = "status=1的总数", example = "0")
  @TableField(exist = false)
  private Integer signinUnAttendance;

  @ApiModelProperty(value = "status=0的总数", example = "0")
  @TableField(exist = false)
  private Integer signinNoAttendance;

  @TableField(exist = false)
  private StudentInfo studentInfo;

  @TableField(exist = false)
  private CourseName courseName;

  @TableField(exist = false)
  private String classNumber;

}

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
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseName对象", description = "")
public class CourseName implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long courseNumber;

  private String courseName;

  @ApiModelProperty(value = "课程简介")
  private String courseContext;

  private Integer courseCount;

  @ApiModelProperty(value = "0代表停课，1代表正常开课")
  private Boolean courseStatus;

  @ApiModelProperty(value = "开课专业")
  private String field;

  @ApiModelProperty(value = "学分", example = "0")
  private Integer credit;

  @ApiModelProperty(value = "学年")
  private String year;

  @ApiModelProperty(value = "学期", example = "0")
  private Integer semester;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private ClassRoomInfo classRoomInfo;

  @TableField(exist = false)
  private List<CourseTimeInfo> courseTimeInfo;

  @TableField(exist = false)
  private Long courseId;

  //我加的
  private Integer courseType;

}

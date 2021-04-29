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
 * 院系表
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FacultyInfo对象", description = "院系表")
public class FacultyInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String facultyName;

  @ApiModelProperty(value = "院系号", example = "0")
  private Integer facultyNumber;

  @ApiModelProperty(value = "外键（连接院校）", example = "0")
  private Long collegeId;

  @ApiModelProperty(value = "院系人数", example = "0")
  private Integer number;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;


}

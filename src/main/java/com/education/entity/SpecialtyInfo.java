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
 * 专业表
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SpecialtyInfo对象", description = "专业表")
public class SpecialtyInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String specialtyId;

  private String specialtyName;

  @ApiModelProperty(value = "外键（院系表）", example = "0")
  private Long facultyId;

  private Integer count;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private FacultyInfo facultyInfo;


}

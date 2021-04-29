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
 * 教室表
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ClassRoomInfo对象", description = "教室表")
public class ClassRoomInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String roomNumber;

  @ApiModelProperty(value = "外键(院系表faculty_info)", example = "0")
  private Long facultyId;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  //我加的
  private String year;

  private Integer semester;

}

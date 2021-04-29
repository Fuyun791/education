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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RolePopedom对象", description = "")
public class RolePopedom implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long roleId;

  private Long popedomId;

  @ApiModelProperty(value = "0为全范围，1为本校，2为院系，3为专业", example = "0")
  private Integer popedomStatus;

  @ApiModelProperty(value = "2为只读,5为可写,7为可读写")
  private Boolean workAuthorize;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;


}

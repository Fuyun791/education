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
 * 访问权限表
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PopedomInfo对象", description = "访问权限表")
public class PopedomInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "如1级，2级，3级")
  private String popedomName;

  @ApiModelProperty(value = "router的英文名")
  private String popedomEname;

  @ApiModelProperty(value = "路径")
  private String popedomUrl;

  @ApiModelProperty(value = "1级目录有icon，2无")
  private String popedomIcon;

  @ApiModelProperty(value = "父菜单", example = "1")
  private Long popedomParent;

  @ApiModelProperty(value = "子菜单，没有为0", example = "1")
  private Long popedomChild;

  private Integer sort;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private List<PopedomInfo> popedomInfoList;


}

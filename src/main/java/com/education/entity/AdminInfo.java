package com.education.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 主要分主管理员和子管理员
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdminInfo对象", description="主要分主管理员和子管理员")
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer adminNumber;

    private String adminName;

    private String password;

    private String phone;

    private String email;

    @ApiModelProperty(value = "外键（角色表）", example = "4")
    private Long roleId;

    @ApiModelProperty(value = "外键（院校表）", example = "-1")
    private Integer collegeId;

    private String adminPic;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private RoleInfo roleInfo;


}

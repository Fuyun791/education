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
 * 课程审核表
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OnlineCourseChecked对象", description="课程审核表")
public class OnlineCourseChecked implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键online_course_info")
    private Long onlineCourseId;

    @ApiModelProperty(value = "外键(后台管理员身份的人)")
    private Long people;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime checkedData;

    @ApiModelProperty(value = "0待审核，1审核中")
    private Integer checkedStatus;

    @ApiModelProperty(value = "0审核通过，1审核不通过")
    private Boolean checkedResult;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;


}

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
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CollegeInfo对象", description="")
public class CollegeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String collegeName;

    @ApiModelProperty(value = "如浙江省xxx")
    private String collegeAddr;

    @ApiModelProperty(value = "不超过300字的简介")
    private String collegeCon;

    @ApiModelProperty(value = "学校图片路径")
    private String collegePic;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;


}

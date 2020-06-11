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
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OnlineClassify对象", description="")
public class OnlineClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文本格式不超过200字")
    private String catalogBrief;

    @ApiModelProperty(value = "分类名称")
    private String levelName;

    @ApiModelProperty(value = "等级,1级，2级", example = "0")
    private Integer level;

    @ApiModelProperty(value = "父级分类,0代表顶层，没有父级分类", example = "0")
    private Long levelParent;

    @ApiModelProperty(value = "子级分类,0代表没有子级分类", example = "0")
    private Long levelChild;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;


}

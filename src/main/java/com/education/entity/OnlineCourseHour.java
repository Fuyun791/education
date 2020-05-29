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
 * 在线课程课时表
 * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OnlineCourseHour对象", description="在线课程课时表")
public class OnlineCourseHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long onlineEpisodesId;

    @ApiModelProperty(value = "课时名称")
    private String courseHourName;

    @ApiModelProperty(value = "这个课时做什么")
    private String courseHourBrief;

    private Integer sort;

    @ApiModelProperty(value = "课时类型，1文字，2ppt，3.视频", example = "0")
    private Integer isType;

    @ApiModelProperty(value = "资源路径")
    private String directory;

    private String hourTime;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;


}

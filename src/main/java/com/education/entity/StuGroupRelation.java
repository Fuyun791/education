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
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StuGroupRelation对象", description="")
public class StuGroupRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键(学生表student_info)")
    private Long studentId;

    @ApiModelProperty(value = "外键(兴趣小组表group_info)")
    private Long groupId;

    @ApiModelProperty(value = "学生加入时间")
    private LocalDateTime joinTime;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;


}

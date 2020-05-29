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
 * 
 * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StudentExam对象", description="")
public class StudentExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键(课程表course_info)")
    private Long courseId;

    @ApiModelProperty(value = "外键(学生表student_info)")
    private Long studentId;

    @ApiModelProperty(value = "考试成绩")
    private Integer score;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    //下面三个是为了多表查询加的
    @TableField(exist = false)
    private StudentInfo studentInfo;

    @TableField(exist = false)
    private ExamInfo examInfo;

    @TableField(exist = false)
    private CourseName courseName;

}

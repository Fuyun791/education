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
 * 
 * </p>
 *
 * @author dell
 * @since 2020-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExamInfo对象", description="")
public class ExamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String examName;

    @ApiModelProperty(value = "外键(课程表course_info)", example = "0")
    private Long courseId;

    @ApiModelProperty(value = "考试人数", example = "0")
    private Integer seatNum;

    @ApiModelProperty(value = "监考老师外键(teacher_info)", example = "0")
    private Long proctorId;

    @ApiModelProperty(value = "授课老师(teacher_info)", example = "0")
    private Long teacherId;

    @ApiModelProperty(value = "考试开始时间")
    private LocalDateTime startExam;

    @ApiModelProperty(value = "考试结束时间")
    private LocalDateTime endExam;

    @ApiModelProperty(value = "考试教室", example = "0")
    private Long roomId;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    //学年和学期
    private String year;

    private Integer semester;
    //下面三个是为了多表查询加的
    @TableField(exist = false)
    private ClassRoomInfo classRoomInfo;

    @TableField(exist = false)
    private TeacherInfo teacherInfo;

    @TableField(exist = false)
    private StudentInfo studentInfo;


}

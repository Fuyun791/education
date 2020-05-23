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
 * 课程表
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CourseInfo对象", description="课程表")
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键(course_name)课程详情", example = "0")
    private Long courseNumber;

    @ApiModelProperty(value = "外键(class_info)教室", example = "0")
    private Long classId;

    @ApiModelProperty(value = "外键(course_time_info)课程时间", example = "0")
    private Long timeCourse;

    @ApiModelProperty(value = "外键(教师表teacher_info)", example = "0")
    private Long teacherId;

    @ApiModelProperty(value = "外键(院校表college_info)", example = "0")
    private Long collegeId;

    @ApiModelProperty(value = "外键(教师表)", example = "0")
    private Long roomId;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    @TableField(exist = false)
    private CourseName courseName;

//    @TableField(exist = false)
//    private List<TeacherInfo> teacherInfo;

    @TableField(exist = false)
    private CollegeInfo collegeInfo;

    @TableField(exist = false)
    private ClassRoomInfo classRoomInfo;

    @TableField(exist = false)
    private List<CourseTimeInfo> courseTimeInfo;

    @TableField(exist = false)
    private List<ClassInfo> classInfo;

}

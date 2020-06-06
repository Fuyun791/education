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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeacherInfo对象", description="")
public class TeacherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer teacherNumber;

    private String teacherName;

    private String teacherSex;

    private String teacherPhone;

    @ApiModelProperty(value = "教师职务")
    private String portfolio;

    @ApiModelProperty(value = "外键(院系表faculty_info)", example = "0")
    private Long facultyId;

    @ApiModelProperty(value = "外键(院校表college_info)", example = "0")
    private Long collegeId;

    private LocalDateTime dataCreate;

    private LocalDateTime dataModified;

    @TableField(exist = false)
    private CollegeInfo collegeInfo;

    @TableField(exist = false)
    private FacultyInfo facultyInfo;

    @TableField(exist = false)
    private List<CourseInfo> courseInfo;

    //我加的
    @TableField(exist = false)
    private Integer year;

    @TableField(exist = false)
    private Integer semester;

}

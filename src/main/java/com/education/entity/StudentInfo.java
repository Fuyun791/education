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
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentInfo对象", description = "")
public class StudentInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Integer studentNum;

  private String studentName;

  private String studentSex;

  private String studentPhone;

  @ApiModelProperty(value = "外键(学校表college_info)", example = "0")
  private Long collegeId;

  @ApiModelProperty(value = "外键(专业表specialty_info)", example = "0")
  private Long specialtyId;

  @ApiModelProperty(value = "外键(院系表faculty_info)", example = "0")
  private Long facultyId;

  @ApiModelProperty(value = "外键(班级表class_info)", example = "0")
  private Long classId;

  private String wechart;

  private LocalDateTime dataCreate;

  private LocalDateTime dataModified;

  @TableField(exist = false)
  private CollegeInfo collegeInfo;

  @TableField(exist = false)
  private SpecialtyInfo specialtyInfo;

  @TableField(exist = false)
  private FacultyInfo facultyInfo;

  @TableField(exist = false)
  private ClassInfo classInfo;

  @TableField(exist = false)
  private List<CourseInfo> courseInfo;

  @TableField(exist = false)
  private String adminName;

  @TableField(exist = false)
  private String adminPic;

  //学年和学期
  @TableField(exist = false)
  private String year;

  @TableField(exist = false)
  private Integer semester;

}

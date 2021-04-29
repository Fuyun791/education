package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.entity.AdminInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.education.entity.StudentInfo;
import com.education.mapper.StudentInfoMapper;
import com.education.service.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements
    IStudentInfoService {

  private final StudentInfoMapper studentInfoMapper;

  private final AdminInfoServiceImpl adminInfoService;

  private final static String ADMIN_PIC = "http://123.57.57.136/jklimage/cat.png";

  @Autowired
  public StudentInfoServiceImpl(StudentInfoMapper studentInfoMapper,
      AdminInfoServiceImpl adminInfoService) {
    this.studentInfoMapper = studentInfoMapper;
    this.adminInfoService = adminInfoService;
  }

  @Override
  public List<StudentInfo> findStudentInfo(StudentInfo studentInfo, Integer pageStart,
      Integer pageSize) {
    //这里根据具体的条件进行扩充
    QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper<>(studentInfo);
    PageHelper.startPage(pageStart, pageSize);
    return studentInfoMapper.selectList(queryWrapper);
  }

  @Override
  public StudentInfo getStudentInfo(Integer studentNum) {
    return studentInfoMapper.getStudentInfo(studentNum);
  }

  @Override
  public List<StudentInfo> findStudentInfoList(StudentInfo studentInfo, Integer pageStart,
      Integer pageSize) {
    PageHelper.startPage(pageStart, pageSize);
    return studentInfoMapper.findStudentInfoList(studentInfo);
  }

  @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
  @Override
  public int insertStudentInfo(StudentInfo studentInfo) {
    int result = 0;
    if (studentInfoMapper.insert(studentInfo) == 1) {
      AdminInfo adminInfo = new AdminInfo();
      adminInfo.setAdminNumber(studentInfo.getStudentNum());
      adminInfo.setAdminName(studentInfo.getStudentName());
      adminInfo.setPassword("123456");
      adminInfo.setPhone(studentInfo.getStudentPhone());
      adminInfo.setRoleId(4L);
      adminInfo.setCollegeId(studentInfo.getCollegeId());
      adminInfo.setAdminPic(ADMIN_PIC);
      result = adminInfoService.insertAdminInfo(adminInfo);
    }
    return result;
  }

  @Override
  public int updateStudentInfo(StudentInfo studentInfo) {
    return studentInfoMapper.updateById(studentInfo);
  }

  @Override
  public int deleteStudentInfo(int id) {
    return studentInfoMapper.deleteById(id);
  }

  @Override
  public int updateStudentPic(AdminInfo adminInfo) {
    UpdateWrapper<AdminInfo> updateWrapper = new UpdateWrapper<>();
    updateWrapper.set("admin_name", adminInfo.getAdminName());
    updateWrapper.set("admin_pic", adminInfo.getAdminPic());
    updateWrapper.set("data_modified", adminInfo.getDataModified());
    updateWrapper.eq("admin_number", adminInfo.getAdminNumber());
    boolean result = adminInfoService.update(updateWrapper);
    return result ? 1 : 0;
  }

}

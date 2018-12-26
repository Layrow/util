package com.niit.service.lms.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsStudentsCn extends BaseRowModel {
    private Integer id;
    @ExcelProperty(value = "姓名" ,index = 0)
    private String studentName;
    @ExcelProperty(value = "英文名" ,index = 1)
    private String studentEngName;

    @ExcelProperty(value = "账号" ,index = 3)
    private String studentUserId;
    @ExcelProperty(value = "密码" ,index = 4)
    private String studentPwd;
    @ExcelProperty(value = "学号" ,index = 2)
    private String studentSchoolId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:SS")
    private Date studentCreateTime;

    private Integer studentStatus;
    private String studentLoginId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentEngName() {
        return studentEngName;
    }

    public void setStudentEngName(String studentEngName) {
        this.studentEngName = studentEngName == null ? null : studentEngName.trim();
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId == null ? null : studentUserId.trim();
    }

    public String getStudentPwd() {
        return studentPwd;
    }

    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd == null ? null : studentPwd.trim();
    }

    public String getStudentSchoolId() {
        return studentSchoolId;
    }

    public void setStudentSchoolId(String studentSchoolId) {
        this.studentSchoolId = studentSchoolId == null ? null : studentSchoolId.trim();
    }

    public Date getStudentCreateTime() {
        return studentCreateTime;
    }

    public void setStudentCreateTime(Date studentCreateTime) {
        this.studentCreateTime = studentCreateTime;
    }

    public Integer getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(Integer studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentLoginId() {
        return studentLoginId;
    }

    public void setStudentLoginId(String studentLoginId) {
        this.studentLoginId = studentLoginId == null ? null : studentLoginId.trim();
    }
}
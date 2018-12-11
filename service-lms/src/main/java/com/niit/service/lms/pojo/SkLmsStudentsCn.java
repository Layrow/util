package com.niit.service.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsStudentsCn  {

    private Integer id;
    private String studentName;
    private String studentEngName;

    private String studentUserId;

    private String studentPwd;
    private String studentSchoolId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
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
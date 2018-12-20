package com.niit.website.smartkids.pojo.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkMemberNotificationOps {
    private Integer id;

    private Integer userId;

    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date noticeTime;

    private Integer projectId;

    private Integer operation;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
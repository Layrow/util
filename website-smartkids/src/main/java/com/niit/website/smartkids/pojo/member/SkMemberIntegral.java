package com.niit.website.smartkids.pojo.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkMemberIntegral {
    private Integer id;

    private Integer userId;

    private String userName;

    private String actions;

    private Integer operation;

    private Integer numbers;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date operationTime;

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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
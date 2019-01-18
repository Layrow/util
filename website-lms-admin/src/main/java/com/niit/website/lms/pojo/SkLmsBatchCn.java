package com.niit.website.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsBatchCn {
    private Integer id;

    private String batchName;

    private Integer batchOrder;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batchStartTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batchEndTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batchCreateTime;

    private String facultyId;

    private Integer batchStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    public Integer getBatchOrder() {
        return batchOrder;
    }

    public void setBatchOrder(Integer batchOrder) {
        this.batchOrder = batchOrder;
    }

    public Date getBatchStartTime() {
        return batchStartTime;
    }

    public void setBatchStartTime(Date batchStartTime) {
        this.batchStartTime = batchStartTime;
    }

    public Date getBatchEndTime() {
        return batchEndTime;
    }

    public void setBatchEndTime(Date batchEndTime) {
        this.batchEndTime = batchEndTime;
    }

    public Date getBatchCreateTime() {
        return batchCreateTime;
    }

    public void setBatchCreateTime(Date batchCreateTime) {
        this.batchCreateTime = batchCreateTime;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId == null ? null : facultyId.trim();
    }

    public Integer getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }
}
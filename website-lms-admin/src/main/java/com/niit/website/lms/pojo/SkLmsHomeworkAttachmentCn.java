package com.niit.website.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsHomeworkAttachmentCn {
    private Integer id;

    private String homeworkAttachmentUrl;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date homeworkAttachmentCreateTime;

    private Integer homeworkAttachmentSize;

    private String homeworkAttachmentSuffix;


    private String homeworkAttachmentTitle;

    public String getHomeworkAttachmentTitle() {
        return homeworkAttachmentTitle;
    }

    public void setHomeworkAttachmentTitle(String homeworkAttachmentTitle) {
        this.homeworkAttachmentTitle = homeworkAttachmentTitle;
    }

    private Integer homeworkId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomeworkAttachmentUrl() {
        return homeworkAttachmentUrl;
    }

    public void setHomeworkAttachmentUrl(String homeworkAttachmentUrl) {
        this.homeworkAttachmentUrl = homeworkAttachmentUrl == null ? null : homeworkAttachmentUrl.trim();
    }

    public Date getHomeworkAttachmentCreateTime() {
        return homeworkAttachmentCreateTime;
    }

    public void setHomeworkAttachmentCreateTime(Date homeworkAttachmentCreateTime) {
        this.homeworkAttachmentCreateTime = homeworkAttachmentCreateTime;
    }

    public Integer getHomeworkAttachmentSize() {
        return homeworkAttachmentSize;
    }

    public void setHomeworkAttachmentSize(Integer homeworkAttachmentSize) {
        this.homeworkAttachmentSize = homeworkAttachmentSize;
    }

    public String getHomeworkAttachmentSuffix() {
        return homeworkAttachmentSuffix;
    }

    public void setHomeworkAttachmentSuffix(String homeworkAttachmentSuffix) {
        this.homeworkAttachmentSuffix = homeworkAttachmentSuffix == null ? null : homeworkAttachmentSuffix.trim();
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }
}
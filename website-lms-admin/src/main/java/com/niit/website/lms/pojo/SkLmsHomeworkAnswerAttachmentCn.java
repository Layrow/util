package com.niit.website.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsHomeworkAnswerAttachmentCn {
    private Integer id;

    private String answerAttachmentTitle;

    private String answerAttachmentUrl;

    private Integer answerAttachmentSize;

    private String answerAttachmentSuffix;

    private Integer homeworkAnswerId;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date answerAttachmentCreateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerAttachmentTitle() {
        return answerAttachmentTitle;
    }

    public void setAnswerAttachmentTitle(String answerAttachmentTitle) {
        this.answerAttachmentTitle = answerAttachmentTitle == null ? null : answerAttachmentTitle.trim();
    }

    public String getAnswerAttachmentUrl() {
        return answerAttachmentUrl;
    }

    public void setAnswerAttachmentUrl(String answerAttachmentUrl) {
        this.answerAttachmentUrl = answerAttachmentUrl == null ? null : answerAttachmentUrl.trim();
    }

    public Integer getAnswerAttachmentSize() {
        return answerAttachmentSize;
    }

    public void setAnswerAttachmentSize(Integer answerAttachmentSize) {
        this.answerAttachmentSize = answerAttachmentSize;
    }

    public String getAnswerAttachmentSuffix() {
        return answerAttachmentSuffix;
    }

    public void setAnswerAttachmentSuffix(String answerAttachmentSuffix) {
        this.answerAttachmentSuffix = answerAttachmentSuffix == null ? null : answerAttachmentSuffix.trim();
    }

    public Date getAnswerAttachmentCreateTime() {
        return answerAttachmentCreateTime;
    }

    public void setAnswerAttachmentCreateTime(Date answerAttachmentCreateTime) {
        this.answerAttachmentCreateTime = answerAttachmentCreateTime;
    }

    public Integer getHomeworkAnswerId() {
        return homeworkAnswerId;
    }

    public void setHomeworkAnswerId(Integer homeworkAnswerId) {
        this.homeworkAnswerId = homeworkAnswerId;
    }
}
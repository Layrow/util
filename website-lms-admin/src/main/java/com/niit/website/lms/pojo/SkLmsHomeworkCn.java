package com.niit.website.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsHomeworkCn {
    private Integer id;

    private String homeworkTitle;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date homeworkCreateTime;

    private String facultyId;

    private Integer homeworkStatus;

    private String homeworkContent;

    private SkLmsHomeworkAttachmentCn skLmsHomeworkAttachmentCn;

    public SkLmsHomeworkAttachmentCn getSkLmsHomeworkAttachmentCn() {
        return skLmsHomeworkAttachmentCn;
    }

    public void setSkLmsHomeworkAttachmentCn(SkLmsHomeworkAttachmentCn skLmsHomeworkAttachmentCn) {
        this.skLmsHomeworkAttachmentCn = skLmsHomeworkAttachmentCn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle == null ? null : homeworkTitle.trim();
    }

    public Date getHomeworkCreateTime() {
        return homeworkCreateTime;
    }

    public void setHomeworkCreateTime(Date homeworkCreateTime) {
        this.homeworkCreateTime = homeworkCreateTime;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId == null ? null : facultyId.trim();
    }

    public Integer getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(Integer homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent == null ? null : homeworkContent.trim();
    }
}
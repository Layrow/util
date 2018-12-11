package com.niit.service.lms.pojo;

import java.util.Date;

public class SkLmsHomeworkAnswerCn {
    private Integer id;

    private Date homeworkAnswerCreateTime;

    private Integer studentId;

    private Integer homeworkAnswerMark;

    private String homeworkAnswerComments;

    private String homeworkAnswerContent;

    private Integer homeworkId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHomeworkAnswerCreateTime() {
        return homeworkAnswerCreateTime;
    }

    public void setHomeworkAnswerCreateTime(Date homeworkAnswerCreateTime) {
        this.homeworkAnswerCreateTime = homeworkAnswerCreateTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getHomeworkAnswerMark() {
        return homeworkAnswerMark;
    }

    public void setHomeworkAnswerMark(Integer homeworkAnswerMark) {
        this.homeworkAnswerMark = homeworkAnswerMark;
    }

    public String getHomeworkAnswerComments() {
        return homeworkAnswerComments;
    }

    public void setHomeworkAnswerComments(String homeworkAnswerComments) {
        this.homeworkAnswerComments = homeworkAnswerComments == null ? null : homeworkAnswerComments.trim();
    }

    public String getHomeworkAnswerContent() {
        return homeworkAnswerContent;
    }

    public void setHomeworkAnswerContent(String homeworkAnswerContent) {
        this.homeworkAnswerContent = homeworkAnswerContent == null ? null : homeworkAnswerContent.trim();
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

}
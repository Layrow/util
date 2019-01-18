package com.niit.website.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkLmsCoursewareCn {
    private Integer id;

    private String coursewareTitle;

    private Integer coursewareSize;

    private String coursewareUrl;

    private String coursewareSuffix;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date coursewareCreateTime;

    private Integer coursewareStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursewareTitle() {
        return coursewareTitle;
    }

    public void setCoursewareTitle(String coursewareTitle) {
        this.coursewareTitle = coursewareTitle == null ? null : coursewareTitle.trim();
    }

    public Integer getCoursewareSize() {
        return coursewareSize;
    }

    public void setCoursewareSize(Integer coursewareSize) {
        this.coursewareSize = coursewareSize;
    }

    public String getCoursewareUrl() {
        return coursewareUrl;
    }

    public void setCoursewareUrl(String coursewareUrl) {
        this.coursewareUrl = coursewareUrl == null ? null : coursewareUrl.trim();
    }

    public String getCoursewareSuffix() {
        return coursewareSuffix;
    }

    public void setCoursewareSuffix(String coursewareSuffix) {
        this.coursewareSuffix = coursewareSuffix == null ? null : coursewareSuffix.trim();
    }

    public Date getCoursewareCreateTime() {
        return coursewareCreateTime;
    }

    public void setCoursewareCreateTime(Date coursewareCreateTime) {
        this.coursewareCreateTime = coursewareCreateTime;
    }

    public Integer getCoursewareStatus() {
        return coursewareStatus;
    }

    public void setCoursewareStatus(Integer coursewareStatus) {
        this.coursewareStatus = coursewareStatus;
    }
}
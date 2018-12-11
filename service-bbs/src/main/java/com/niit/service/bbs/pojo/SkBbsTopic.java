package com.niit.service.bbs.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkBbsTopic {
    private Integer id;

    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    private String createUserid;

    private Integer viewcount;

    private Integer isoffcial;

    private Integer istop;

    private Integer isessence;

    private Integer status;

    private Integer sectionId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Integer getIsoffcial() {
        return isoffcial;
    }

    public void setIsoffcial(Integer isoffcial) {
        this.isoffcial = isoffcial;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getIsessence() {
        return isessence;
    }

    public void setIsessence(Integer isessence) {
        this.isessence = isessence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
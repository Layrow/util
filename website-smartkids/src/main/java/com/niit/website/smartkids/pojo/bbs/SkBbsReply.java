package com.niit.website.smartkids.pojo.bbs;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SkBbsReply {
    private Integer id;

    private String replyUserid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date replyTime;

    private Integer replyOrder;

    private Integer topicId;

    private String content;

    private Integer replyStatus;

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReplyUserid() {
        return replyUserid;
    }

    public void setReplyUserid(String replyUserid) {
        this.replyUserid = replyUserid == null ? null : replyUserid.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyOrder() {
        return replyOrder;
    }

    public void setReplyOrder(Integer replyOrder) {
        this.replyOrder = replyOrder;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
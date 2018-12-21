package com.niit.website.smartkids.enums;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/19
 * @since 1.0.0
 */
public enum IntegralActionsEnum {

    /**
     * 操作枚举
     */
    POST_TOPIC(1, 50, "发表帖子"),
    POST_REPLY(1, 20, "发表评论"),
    POSTTOP(1, 50, "置顶贴"),
    POSTESSENCE(1, 50, "精华帖"),
    POST_BUY(1, 50, "购买"),
    POST_COLLECT(1, 50, "收藏作品"),
    POST_LIKE(1, 50, "项目点赞"),
    POST_PROJECT(1, 50, "上传作品"),
    DELETE_BAD(0, 50, "不雅言论");
    /**
     * 操作类型
     */
    private int operation;
    /**
     * 操作分数
     */
    private int nums;
    /**
     * 操作行为描述
     */
    private String action;

    private IntegralActionsEnum(int operation, int nums, String action) {
        this.operation = operation;
        this.nums = nums;
        this.action = action;
    }


    public int getOperation() {
        return operation;
    }

    public int getNums() {
        return nums;
    }

    public String getAction() {
        return action;
    }
}

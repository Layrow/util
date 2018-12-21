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
    DELETE_TOPIC(0, 50, "帖子被删除"),
    POST_REPLY(1, 20, "发表评论"),
    DELETE_REPLY(0, 50, "评论被删除"),
    POSTTOP(1, 50, "置顶贴"),
    POST_UPTOP(0, 50, "取消置顶贴"),
    POSTESSENCE(1, 50, "精华帖"),
    POST_UNESSENCE(0, 50, "取消精华帖"),
    POST_BUY(1, 50, "购买"),
    POST_LIKE(1, 50, "点赞"),
    POST_UNLIKE(0, 50, "取消点赞"),
    POST_COLLECT(1, 50, "收藏作品"),
    POST_UNCOLLECT(0, 50, "取消收藏作品"),
    POST_PROJECT(1, 50, "上传作品"),
    POST_UNPROJECT(0, 50, "撤回作品"),
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

package com.niit.website.smartkids.enums;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
public enum NotificationEnum {

    /**
     * 通知的三种动作
     */
    //购买
    PURCHASE(1),
    //点赞
    LIKE(2),
    //收藏
    COLLECT(3);

    private int operation;

    private NotificationEnum(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }
}

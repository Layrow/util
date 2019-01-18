package com.niit.service.member.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.member.pojo.SkMemberNotificationOps;
import org.apache.ibatis.annotations.Param;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
public interface ISkMemberNotificationService {
    /**
     * 添加一条通知
     *
     * @param record
     * @return
     */
    boolean insertNotification(SkMemberNotificationOps record);

    /**
     * 根据通知主键删除通知
     *
     * @param notificationId
     * @return
     */
    boolean deleteNotification(Integer notificationId);

    int deleteNotification(Integer user_id, Integer project_id, Integer operation);

    /**
     * 查询用户下的所有通知
     *
     * @param pageSize    页面大小
     * @param currentPage 当前页
     * @param userId
     * @return
     */
    PageInfo<SkMemberNotificationOps> listAll(Integer currentPage, Integer pageSize, Integer userId);

}

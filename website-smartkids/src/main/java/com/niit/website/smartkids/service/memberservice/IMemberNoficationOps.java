package com.niit.website.smartkids.service.memberservice;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationOps;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
public interface IMemberNoficationOps {
    /**
     * 添加一条通知
     * @param record
     * @return
     */
    boolean insertNotification(SkMemberNotificationOps record);

    /**
     * 根据通知主键删除通知
     * @param nId
     * @return
     */
    boolean deleteNotification(Integer nId);

    /**
     * 查询用户下的所有通知
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @param uId
     * @return
     */
    PageInfo<SkMemberNotificationOps> listAll(Integer currentPage, Integer pageSize, Integer uId);

}

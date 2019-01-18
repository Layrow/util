package com.niit.service.member.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.member.pojo.SkMemberNotificationSystem;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
public interface ISkMemberSysNotificationService {

    /**
     * 增加一条系统通知
     *
     * @param record
     * @return
     */
    boolean add(SkMemberNotificationSystem record);

    /**
     * 删除条系统通知
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 修改一条系统通知
     *
     * @param record
     * @return
     */
    boolean update(SkMemberNotificationSystem record);

    /**
     * 显示所有的 系统通知
     *
     * @return
     */
    PageInfo<SkMemberNotificationSystem> listAll(Integer currentPage, Integer pageSize);
}

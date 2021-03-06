package com.niit.service.member.dao;

import com.niit.service.member.pojo.SkMemberNotificationOps;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkMemberNotificationOpsMapper {

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int deleteNotification(@Param("user_id") Integer user_id,
                           @Param("project_id") Integer project_id,
                           @Param("operation") Integer operation);

    /**
     * 显示用户的所有通知
     *
     * @param userId
     * @return
     */
    List<SkMemberNotificationOps> selectAll(Integer userId);

    /**
     * 添加一条通知
     *
     * @param record
     * @return
     */
    int insert(SkMemberNotificationOps record);


    int insertSelective(SkMemberNotificationOps record);

    SkMemberNotificationOps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberNotificationOps record);

    int updateByPrimaryKey(SkMemberNotificationOps record);
}
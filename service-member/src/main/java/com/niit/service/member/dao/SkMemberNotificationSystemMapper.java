package com.niit.service.member.dao;

import com.niit.service.member.pojo.SkMemberNotificationSystem;

public interface SkMemberNotificationSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkMemberNotificationSystem record);

    int insertSelective(SkMemberNotificationSystem record);

    SkMemberNotificationSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberNotificationSystem record);

    int updateByPrimaryKey(SkMemberNotificationSystem record);
}
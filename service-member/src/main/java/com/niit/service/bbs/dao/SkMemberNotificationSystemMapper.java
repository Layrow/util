package com.niit.service.bbs.dao;

import com.niit.service.bbs.pojo.SkMemberNotificationSystem;

public interface SkMemberNotificationSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkMemberNotificationSystem record);

    int insertSelective(SkMemberNotificationSystem record);

    SkMemberNotificationSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberNotificationSystem record);

    int updateByPrimaryKey(SkMemberNotificationSystem record);
}
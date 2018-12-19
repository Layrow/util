package com.niit.service.member.dao;

import com.niit.service.member.pojo.SkMemberNotificationOps;

public interface SkMemberNotificationOpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkMemberNotificationOps record);

    int insertSelective(SkMemberNotificationOps record);

    SkMemberNotificationOps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberNotificationOps record);

    int updateByPrimaryKey(SkMemberNotificationOps record);
}
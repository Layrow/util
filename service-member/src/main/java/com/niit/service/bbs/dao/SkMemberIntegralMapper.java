package com.niit.service.bbs.dao;

import com.niit.service.bbs.pojo.SkMemberIntegral;

public interface SkMemberIntegralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkMemberIntegral record);

    int insertSelective(SkMemberIntegral record);

    SkMemberIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberIntegral record);

    int updateByPrimaryKey(SkMemberIntegral record);
}
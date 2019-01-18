package com.niit.service.member.dao;

import com.niit.service.member.pojo.SkMemberIntegral;

import java.util.List;

public interface SkMemberIntegralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkMemberIntegral record);

    /**
     * 积分变动,每一次的更新插入都会影响到这里
     *
     * @param record
     * @return
     */
    int insertSelective(SkMemberIntegral record);

    /**
     * 查询正积分
     *
     * @param userId
     * @return
     */
    Integer selectAddIntegral(Integer userId);

    /**
     * 查询负积分
     *
     * @param userId
     * @return
     */
    Integer selectDelIntegral(Integer userId);

    SkMemberIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkMemberIntegral record);

    int updateByPrimaryKey(SkMemberIntegral record);
}
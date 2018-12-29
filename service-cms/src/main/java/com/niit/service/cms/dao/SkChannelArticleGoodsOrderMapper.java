package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleGoodsOrder;

import java.util.List;

public interface SkChannelArticleGoodsOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleGoodsOrder record);

    int insertSelective(SkChannelArticleGoodsOrder record);

    SkChannelArticleGoodsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleGoodsOrder record);

    int updateByPrimaryKey(SkChannelArticleGoodsOrder record);

    List<SkChannelArticleGoodsOrder> selectByUserId(Integer userId);
}
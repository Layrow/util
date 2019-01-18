package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;

import java.util.List;

public interface SkChannelArticleGoodsCostumesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleGoodsCostumes record);

    int insertSelective(SkChannelArticleGoodsCostumes record);

    SkChannelArticleGoodsCostumes selectByPrimaryKey(Integer id);

    List<SkChannelArticleGoodsCostumes> selectByGoodId(Integer goodId);

    List<SkChannelArticleGoodsCostumes> selectByGoodsId(List<Integer> goodsId);

    List<SkChannelArticleGoodsCostumes> selectSpritesByGoodsId(List<Integer> goodsId);

    List<SkChannelArticleGoodsCostumes> selectBackdropsByGoodsId(List<Integer> goodsId);

    int updateByPrimaryKeySelective(SkChannelArticleGoodsCostumes record);

    int updateByPrimaryKey(SkChannelArticleGoodsCostumes record);
}
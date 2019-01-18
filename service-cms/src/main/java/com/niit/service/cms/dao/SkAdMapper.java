package com.niit.service.cms.dao;


import com.niit.service.cms.pojo.SkAd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkAdMapper {
    // 根据ID删除广告位
    int deleteByPrimaryKey(Integer id);

    // 添加广告位
    int insert(SkAd record);

    int insertSelective(SkAd record);

    SkAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkAd record);

    int updateByPrimaryKey(SkAd record);

    // 查询所有广告位(可用/非可用)
    List<SkAd> selectAllAdsense(@Param("status") Integer status);

    // 查询所有广告位(直接查询所有广告位信息)
    List<SkAd> selectAllAdsenses();

    // 批量删除广告位
    int deleteMoreAd(List<String> list);

    // 根据title模糊查询广告位
    List<SkAd> likeSelectAdAllByTitle(String title);

}
package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkAdContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SkAdContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkAdContent record);

    // 新增特定广告位下的广告
    int insertSelective(SkAdContent record);

    SkAdContent selectByPrimaryKey(Integer id);

    // 审核广告
    int updateByPrimaryKeySelective(SkAdContent record);

    int updateByPrimaryKey(SkAdContent record);

    // 删除某个广告位下的所有广告
    int deleteByAdId(Integer adId);

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    List<SkAdContent> selectByAdId(@Param("ad_id") Integer adId, @Param("ad_status") Integer status);

    // 批量审核广告
    int updateMoreAdContent(List<String> list);

    // 批量删除广告
    int deleteMoreAdContent(List<String> list);

    // 批量修改排序值
    int updateAdContentMoreSortId(List<SkAdContent> skAdContentList);

    // 查询所有广告
    List<LinkedHashMap<String,Object>> selectAllAd(@Param("title") String title);

}
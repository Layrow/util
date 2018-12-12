package com.niit.service.cms.service;

import com.niit.service.cms.pojo.SkAd;
import com.niit.service.cms.pojo.SkAdContent;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 10:02
 **/
public interface SkAdService {

    // 查询所有广告位(可用/非可用)
    List<SkAd> selectAllAdsense(Integer status);

    // 根据ID批量删除广告位
    int deleteMoreAd(String id);

    // 根据ID删除广告
    int deleteAdContentByPrimaryKey(Integer id);

    // 新增特定广告位下的广告
    int insertAdContentSelective(SkAdContent record);

    // 审核广告
    int updateByPrimaryKeySelective(SkAdContent record);

    // 删除某个广告位下的所有广告
    int deleteByAdId(Integer adId);

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    List<SkAdContent> selectByAdId(Integer adId, Integer status);

    // 添加广告位
    int insert(SkAd record);

    // 批量审核广告
    int updateMoreAdContent(String id);

    // 批量删除广告
    int deleteMoreAdContent(String id);

}

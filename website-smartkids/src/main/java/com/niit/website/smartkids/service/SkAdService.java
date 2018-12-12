package com.niit.website.smartkids.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkAd;
import com.niit.website.smartkids.pojo.SkAdContent;


/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 16:13
 **/
public interface SkAdService {

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    PageInfo<SkAdContent> selectByAdId(Integer adId, Integer status,Integer currentPage,Integer pageSize);

    // 查询所有广告位(可用/非可用)
    PageInfo<SkAd> selectAllAdsense(Integer status,Integer currentPage,Integer pageSize);
}

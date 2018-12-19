package com.niit.website.cms.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkAd;
import com.niit.website.cms.pojo.SkAdContent;
import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 10:02
 **/
public interface SkAdService {

    // 查询所有广告位(可用/非可用)
    PageInfo<SkAd> selectAllAdsense(Integer status,Integer currentPage,Integer pageSize);

    // 查询所有广告位(直接查询所有广告位信息)
    List<SkAd> selectAllAdsenses();

    // 根据ID批量删除广告位
    void deleteMoreAd(String id);

    // 根据ID删除广告
    void deleteAdContentByPrimaryKey(Integer id);

    // 新增特定广告位下的广告
    void insertAdContentSelective(SkAdContent record);

    // 修改广告内容
    void updateAdContentByPrimaryKeySelective(SkAdContent record);

    // 修改广告位内容
    void updateAd(SkAd skAd);

    // 删除某个广告位下的所有广告
    void deleteByAdId(Integer adId);

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    PageInfo<SkAdContent> selectByAdId(Integer adId, Integer status, Integer currentPage, Integer pageSize);

    // 根据keycode查询广告 order排序 status为1 或 status为0
    PageInfo<SkAdContent> selectByKeycode(String keycode, Integer status,Integer currentPage,Integer pageSize);

    // 添加广告位
    void insert(SkAd record);

    // 批量审核广告
    void updateMoreAdContent(String id);

    // 批量删除广告
    void deleteMoreAdContent(String id);

    // 根据title模糊查询广告位
    PageInfo<SkAd> likeSelectAdAllByTitle(String title,Integer currentPage,Integer pageSize);

    // 批量修改排序值
    void updateAdContentMoreSortId(List<SkAdContent> skAdContentList);

    // 根据广告位title模糊查询广告
    String selectAllAd(Integer status,String title,Integer currentPage,Integer pageSize);

}

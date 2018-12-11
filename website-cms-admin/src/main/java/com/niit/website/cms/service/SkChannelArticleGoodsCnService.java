package com.niit.website.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleGoodsCn;

import java.util.List;

public interface SkChannelArticleGoodsCnService {
   // int deleteByPrimaryKey(Integer id);

   // int insert(SkChannelArticleGoodsCn record);

    int insertSelective(SkChannelArticleGoodsCn record,String locale);

    //SkChannelArticleGoodsCn selectByPrimaryKey(Integer id);

      int updateByPrimaryKeySelective(SkChannelArticleGoodsCn record,String locale);

   // int updateByPrimaryKeyWithBLOBs(SkChannelArticleGoodsCn record);

    //int updateByPrimaryKey(SkChannelArticleGoodsCn record);
    //批量删除
    void deleteAd(String id,String locale);
   //分页查找所有
   PageInfo<SkChannelArticleGoodsCn> selectAll(int currentPage, int pageSize,String locale);
    PageInfo<SkChannelArticleGoodsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);

    //批量审核
    void updateSt(String id,String locale);
    //批量更新
    void  updateTo(String id,String locale);
    //批量推荐
    void  updateRe(String id,String locale);
    //批量热门
    void  updateHo(String id,String locale);

    //分页模糊查询
    PageInfo<SkChannelArticleGoodsCn> likeSelectAll(Integer categoryId,String key,int currentPage ,int pageSize ,String title,String locale,Integer channelId);
    String batchUp(List<SkChannelArticleGoodsCn> lis, String locale);
}

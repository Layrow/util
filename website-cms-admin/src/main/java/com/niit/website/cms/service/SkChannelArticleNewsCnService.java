package com.niit.website.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleNewsCn;

import java.util.List;

public interface SkChannelArticleNewsCnService {
    //分页模糊查询
    PageInfo<SkChannelArticleNewsCn> likeSelectAll(Integer categoryId, String key, int currentPage , int pageSize , String title, String locale);

    // int deleteByPrimaryKey(Integer id);

   // int insert(SkChannelArticleNewsCn record);

    int insertSelective(SkChannelArticleNewsCn record,String locale);

    //SkChannelArticleNewsCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleNewsCn record,String locale);

   // int updateByPrimaryKeyWithBLOBs(SkChannelArticleNewsCn record);

    //int updateByPrimaryKey(SkChannelArticleNewsCn record);
   //分页查找所有
    PageInfo<SkChannelArticleNewsCn> selectAll(int currentPage, int pageSize,String locale);
    PageInfo<SkChannelArticleNewsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);



   //批量删除
    void deleteAd(String id,String locale);
   //批量更新
    void updateSt(String id,String locale);
    //批量更新
    void  updateTo(String id,String locale);
    //批量推荐
    void  updateRe(String id,String locale);
    //批量热门
    void  updateHo(String id,String locale);

    /*//分页模糊查询
    PageInfo<SkChannelArticleNewsCn> likeSelectAll(  int currentPage ,int pageSize ,String title,String locale);
    */
    String batchUp(List<SkChannelArticleNewsCn> lis, String locale);
}

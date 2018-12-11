package com.niit.service.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;

import java.util.List;

public interface SkChannelArticleNewsCnService {
    //分页模糊查询
    PageInfo<SkChannelArticleNewsCn> likeSelectAll(int currentPage , int pageSize , String title, String locale, Integer categoryId, String key);

    int deleteByPrimaryKey(Integer id,String locale);

    int insert(SkChannelArticleNewsCn record,String locale);

    int insertSelective(SkChannelArticleNewsCn record,String locale);

    SkChannelArticleNewsCn selectByPrimaryKey(Integer id,String locale);

    int updateByPrimaryKeySelective(SkChannelArticleNewsCn record,String locale);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleNewsCn record,String locale);

    int updateByPrimaryKey(SkChannelArticleNewsCn record,String locale);

    //分页模糊查询
    PageInfo<SkChannelArticleNewsCn> likeSelectAll(  int currentPage ,int pageSize ,String title,String locale);

    PageInfo<SkChannelArticleNewsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);
   //分页查找所有
    PageInfo<SkChannelArticleNewsCn> selectAll(int currentPage, int pageSize,String locale);

   //批量删除
    void deleteAd(String id,String locale);
   //批量更新
    void updateSt(String id,String locale);
    //批量修改
    int batchUp(List<SkChannelArticleNewsCn> lis, String locale);
    //批量更新
    void  updateTo(String id,String locale);
    //批量推荐
    void  updateRe(String id,String locale);
    //批量热门
    void  updateHo(String id,String locale);

    // 按照栏目类别ID并且status = 1查找新闻
    String selectNewsByCategoryId(String locale,Integer categoryId,Integer currentPage,Integer pageSize);

}

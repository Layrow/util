package com.niit.website.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleVideoCn;

import java.util.List;

public interface SkChannelArticleVideoCnService {
    //分页模糊查询
    PageInfo<SkChannelArticleVideoCn> likeSelectAll(Integer categoryId, String key, int currentPage , int pageSize , String title, String locale);

    //int deleteByPrimaryKey(Integer id);

  //  int insert(SkChannelArticleVideoCn record);

    int insertSelective(SkChannelArticleVideoCn record,String locale);

  //  SkChannelArticleVideoCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleVideoCn record,String locale);

    //int updateByPrimaryKeyWithBLOBs(SkChannelArticleVideoCn record);

   // int updateByPrimaryKey(SkChannelArticleVideoCn record);
  //分页查找所有
    PageInfo<SkChannelArticleVideoCn> selectAll(int currentPage, int pageSize,String locale);

    PageInfo<SkChannelArticleVideoCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);
   //批量删除
    void deleteAd(String id,String locale);
   //批量审核
    void updateSt(String id,String locale);
    //批量更新
    void  updateTo(String id,String locale);
    //批量推荐
    void  updateRe(String id,String locale);
    //批量热门
    void  updateHo(String id,String locale);

    /*//分页模糊查询
    PageInfo<SkChannelArticleVideoCn> likeSelectAll(  int currentPage ,int pageSize ,String title,String locale);
    */
    String batchUp(List<SkChannelArticleVideoCn> lis, String locale);
}

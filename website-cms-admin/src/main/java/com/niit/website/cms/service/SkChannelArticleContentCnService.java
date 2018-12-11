package com.niit.website.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleContentCn;

import java.util.List;


public interface SkChannelArticleContentCnService {

    //分页模糊查询
    PageInfo< SkChannelArticleContentCn> likeSelectAll(Integer categoryId, String key, int currentPage , int pageSize , String title, String locale);
    //int deleteByPrimaryKey(Integer id);

   // int insert(SkChannelArticleContentCn record);

    int insertSelective(SkChannelArticleContentCn record,String locale);

   // SkChannelArticleContentCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleContentCn record,String locale);

   // int updateByPrimaryKeyWithBLOBs(SkChannelArticleContentCn record);

     // int updateByPrimaryKey(SkChannelArticleContentCn record);
    //分页查找所有
//    PageInfo<SkChannelArticleContentCn> selectAll(int currentPage, int pageSize);
    PageInfo<SkChannelArticleContentCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);

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


 /*   //分页模糊查询
    PageInfo<SkChannelArticleContentCn> likeSelectAll(  int currentPage ,int pageSize ,String title,String locale);
*/
    String batchUp(List<SkChannelArticleContentCn> lis, String locale);
}

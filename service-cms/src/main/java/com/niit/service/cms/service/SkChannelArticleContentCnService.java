package com.niit.service.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkChannelArticleContentCn;

import java.util.List;


public interface SkChannelArticleContentCnService {
    //分页模糊查询
    PageInfo<SkChannelArticleContentCn> likeSelectAll(int currentPage , int pageSize , String title, String locale, Integer categoryId, String key);

    int deleteByPrimaryKey(Integer id,String locale);

    int insert(SkChannelArticleContentCn record,String locale);

    int insertSelective(SkChannelArticleContentCn record,String locale);

    SkChannelArticleContentCn selectByPrimaryKey(Integer id,String locale);

    int updateByPrimaryKeySelective(SkChannelArticleContentCn record,String locale);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleContentCn record,String locale);

    int updateByPrimaryKey(SkChannelArticleContentCn record,String locale);

    PageInfo<SkChannelArticleContentCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale);


//    //分页模糊查询
//    PageInfo<SkChannelArticleContentCn> likeSelectAll(  int currentPage ,int pageSize ,String title,String locale);


    //分页查找所有
    PageInfo<SkChannelArticleContentCn> selectAll(int currentPage, int pageSize,String locale);
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
    //批量修改
    int batchUp(List<SkChannelArticleContentCn> lis, String locale);

    // 按照栏目类别ID并且status = 1
    String selectContentByCategoryId(String locale,Integer categoryId,Integer currentPage,Integer pageSize);
}


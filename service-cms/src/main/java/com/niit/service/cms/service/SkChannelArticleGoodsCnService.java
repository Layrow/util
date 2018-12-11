package com.niit.service.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCn;

import java.util.List;
import java.util.Map;

public interface SkChannelArticleGoodsCnService {
    Map<String,Object> easyLikeSelectAll(String locale, String title, Integer channelId);

    //通过父类 拿到父类栏目的内容（商品、新闻）总数
    Map<String,Object> getArticleCountByCategory(Integer channelId, String locale,String keyword);

    int deleteByPrimaryKey(Integer id,String locale);

    int insert(SkChannelArticleGoodsCn record,String locale);

    int insertSelective(SkChannelArticleGoodsCn record,String locale);

    SkChannelArticleGoodsCn selectByPrimaryKey(Integer id,String locale);

    int updateByPrimaryKeySelective(SkChannelArticleGoodsCn record,String locale);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleGoodsCn record,String locale);

    int updateByPrimaryKey(SkChannelArticleGoodsCn record,String locale);
    //批量删除
    void deleteAd(String id,String locale);

    PageInfo<SkChannelArticleGoodsCn> FuzzySearchBy(Integer categoryId,String key,int currentPage, int pageSize,String locale);

    //分页模糊查询
    PageInfo<Object> likeSelectAll(int currentPage , int pageSize , String title, String locale, Integer categoryId, String key, Integer channelId,String orderBy);

    //分页查找所有
    PageInfo<SkChannelArticleGoodsCn> selectAll(int currentPage, int pageSize,String locale);
    //批量审核
    void updateSt(String id,String locale);
    //批量修改
    int batchUp(List<SkChannelArticleGoodsCn> lis, String locale);
    //批量更新
    void  updateTo(String id,String locale);
    //批量推荐
    void  updateRe(String id,String locale);
    //批量热门
    void  updateHo(String id,String locale);
}

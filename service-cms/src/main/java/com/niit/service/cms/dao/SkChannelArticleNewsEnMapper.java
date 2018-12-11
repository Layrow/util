package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SkChannelArticleNewsEnMapper {
    //分页模糊查询 ByCategoryId
    List<SkChannelArticleNewsCn> likeSelectAllByCategoryId(@Param("title") String title, @Param("key") String key, @Param("list") List categoryIdList);

    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleNewsCn record);

    int insertSelective(SkChannelArticleNewsCn record);

    SkChannelArticleNewsCn selectByPrimaryKey(Integer id);

    //分页模糊查询
    List<SkChannelArticleNewsCn> likeSelectAll(String title);

    int updateByPrimaryKeySelective(SkChannelArticleNewsCn record);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleNewsCn record);

    int updateByPrimaryKey(SkChannelArticleNewsCn record);
    //批量审核
    void updateSt(List<String> list);
    //批量删除
    void  deleteAd(List<String> list);
    //分页查找所有
    List<SkChannelArticleNewsCn> selectAll();
    //分页查找所有Hot
    List<SkChannelArticleNewsCn> selectAllByHot();
    //分页查找所有Red
    List<SkChannelArticleNewsCn> selectAllByRed();
    //分页查找所有Top
    List<SkChannelArticleNewsCn> selectAllByTop();
    List<SkChannelArticleNewsCn> selectAllByAudited();
    List<SkChannelArticleNewsCn> selectAllByUnaudited();
    int batchUp(List<SkChannelArticleNewsCn> lis);
    //批量更新
    void  updateTo(List<String> list);
    //批量推荐
    void  updateRe(List<String> list);
    //批量热门
    void  updateHo(List<String> list);

    // 按照栏目类别ID并且status = 1查找新闻
    List<SkChannelArticleNewsCn> selectNewsByCategoryId(List<String> list);

}
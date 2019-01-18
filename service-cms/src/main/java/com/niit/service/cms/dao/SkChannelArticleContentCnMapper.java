package com.niit.service.cms.dao;


import com.niit.service.cms.pojo.SkChannelArticleContentCn;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SkChannelArticleContentCnMapper {
    //分页模糊查询 ByCategoryId
    List<SkChannelArticleContentCn> likeSelectAllByCategoryId(@Param("title") String title, @Param("key") String key, @Param("list") List categoryIdList);

    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleContentCn record);

    int insertSelective(SkChannelArticleContentCn record);

    SkChannelArticleContentCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleContentCn record);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleContentCn record);

    int updateByPrimaryKey(SkChannelArticleContentCn record);

    //分页模糊查询
    List<SkChannelArticleContentCn> likeSelectAll(String title);

    //批量审核
    void updateSt(List<String> list);

    //批量删除
    void deleteAd(List<String> list);

    //批量更新
    void updateTo(List<String> list);

    //批量推荐
    void updateRe(List<String> list);

    //批量热门
    void updateHo(List<String> list);

    //分页查找所有
    List<SkChannelArticleContentCn> selectAll();

    //分页查找所有Hot
    List<SkChannelArticleContentCn> selectAllByHot();

    //分页查找所有Red
    List<SkChannelArticleContentCn> selectAllByRed();

    //分页查找所有Top
    List<SkChannelArticleContentCn> selectAllByTop();

    List<SkChannelArticleContentCn> selectAllByAudited();

    List<SkChannelArticleContentCn> selectAllByUnaudited();

    int batchUp(List<SkChannelArticleContentCn> lis);

    // 按照栏目类别ID并且status = 1查找新闻
    List<SkChannelArticleContentCn> selectContentByCategoryId(List<String> list);
}
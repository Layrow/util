package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleVideoCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkChannelArticleVideoCnMapper {
    //分页模糊查询 ByCategoryId
    List<SkChannelArticleVideoCn> likeSelectAllByCategoryId(@Param("title") String title, @Param("key") String key, @Param("list") List categoryIdList);

    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleVideoCn record);

    int insertSelective(SkChannelArticleVideoCn record);

    SkChannelArticleVideoCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleVideoCn record);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleVideoCn record);

    //分页模糊查询
    List<SkChannelArticleVideoCn> likeSelectAll(String title);

    int updateByPrimaryKey(SkChannelArticleVideoCn record);
    //批量审核
    void updateSt(List<String> list);
    //批量删除
    void  deleteAd(List<String> list);
    //分页查找所有
    List<SkChannelArticleVideoCn> selectAll();

    //分页查找所有Hot
    List<SkChannelArticleVideoCn> selectAllByHot();
    //分页查找所有Red
    List<SkChannelArticleVideoCn> selectAllByRed();
    //分页查找所有Top
    List<SkChannelArticleVideoCn> selectAllByTop();
    List<SkChannelArticleVideoCn> selectAllByAudited();
    List<SkChannelArticleVideoCn> selectAllByUnaudited();
    int batchUp(List<SkChannelArticleVideoCn> lis);
    //批量更新
    void  updateTo(List<String> list);
    //批量推荐
    void  updateRe(List<String> list);
    //批量热门
    void  updateHo(List<String> list);
}
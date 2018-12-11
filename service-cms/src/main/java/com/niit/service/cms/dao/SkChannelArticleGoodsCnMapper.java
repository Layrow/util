package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleGoodsCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkChannelArticleGoodsCnMapper {
    List<Integer> selectEachCountByMainCategoryId(@Param("title") String title,@Param("keyy") String key,@Param("mainCategoryChildrenListMap") Map<Integer,List<Integer>> mainCategoryChildrenListMap);

    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelArticleGoodsCn record);

    int insertSelective(SkChannelArticleGoodsCn record);

    SkChannelArticleGoodsCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelArticleGoodsCn record);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleGoodsCn record);

    int updateByPrimaryKey(SkChannelArticleGoodsCn record);

    //分页模糊查询
    List<SkChannelArticleGoodsCn> likeSelectAll(String title);

    //分页模糊查询 ByCategoryId
    List<SkChannelArticleGoodsCn> likeSelectAllByCategoryId(@Param("title") String title,@Param("key") String key,@Param("list") List categoryIdList,@Param("orderBy") String orderBy);

    //分页查找所有 ByCategoryId
    List<SkChannelArticleGoodsCn> selectAllByCategoryId(@Param("categoryId") Integer categoryId,@Param("key") String key);


    //批量更新
    void  updateTo(List<String> list);
    //批量推荐
    void  updateRe(List<String> list);
    //批量热门
    void  updateHo(List<String> list);
    //批量审核
    void updateSt(List<String> list);
    //批量删除
    void  deleteAd(List<String> list);
    //分页查找所有
    List<SkChannelArticleGoodsCn> selectAll();
    //分页查找所有Hot
    List<SkChannelArticleGoodsCn> selectAllByHot();
    //分页查找所有Red
    List<SkChannelArticleGoodsCn> selectAllByRed();
    //分页查找所有Top
    List<SkChannelArticleGoodsCn> selectAllByTop();
    List<SkChannelArticleGoodsCn> selectAllByAudited();
    List<SkChannelArticleGoodsCn> selectAllByUnaudited();
    int batchUp(List<SkChannelArticleGoodsCn> lis);
}
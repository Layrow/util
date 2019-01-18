package com.niit.service.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkChannelArticleVideoCn;

import java.util.List;

public interface SkChannelArticleVideoCnService {
    //分页模糊查询
    PageInfo<SkChannelArticleVideoCn> likeSelectAll(int currentPage, int pageSize, String title, String locale, Integer categoryId, String key);

    int deleteByPrimaryKey(Integer id, String locale);

    int insert(SkChannelArticleVideoCn record, String locale);

    int insertSelective(SkChannelArticleVideoCn record, String locale);

    SkChannelArticleVideoCn selectByPrimaryKey(Integer id, String locale);

    int updateByPrimaryKeySelective(SkChannelArticleVideoCn record, String locale);

    int updateByPrimaryKeyWithBLOBs(SkChannelArticleVideoCn record, String locale);

    int updateByPrimaryKey(SkChannelArticleVideoCn record, String locale);

    PageInfo<SkChannelArticleVideoCn> FuzzySearchBy(String key, int currentPage, int pageSize, String locale);

    //分页模糊查询
    PageInfo<SkChannelArticleVideoCn> likeSelectAll(int currentPage, int pageSize, String title, String locale);

    //分页查找所有
    PageInfo<SkChannelArticleVideoCn> selectAll(int currentPage, int pageSize, String locale);

    //批量更新
    void deleteAd(String id, String locale);

    //批量审核
    void updateSt(String id, String locale);

    //批量修改
    int batchUp(List<SkChannelArticleVideoCn> lis, String locale);

    //批量更新
    void updateTo(String id, String locale);

    //批量推荐
    void updateRe(String id, String locale);

    //批量热门
    void updateHo(String id, String locale);

    // 按照栏目类别ID并且status = 1
    String selectVideoByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize);
}

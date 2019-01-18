package com.niit.website.smartkids.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleNewsCn;

import java.util.List;


public interface SkChannelArticleNewsCnService {

    // 按照栏目类别ID并且status = 1查找新闻
    String selectNewsByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize);

    // 查询channel为1的，parent_id为0的栏目类别
    List<SkArticleCategoryCn> selectCategory(String locale, Integer channelId);

    // 查询单条新闻
    SkChannelArticleNewsCn selectByPrimaryKeyInfo(Integer id, String locale);

}

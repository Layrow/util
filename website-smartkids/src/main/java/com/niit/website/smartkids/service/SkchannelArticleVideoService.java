package com.niit.website.smartkids.service;

import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleNewsCn;
import com.niit.website.smartkids.pojo.SkChannelArticleVideoCn;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.23 09:54
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.23 09:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface SkchannelArticleVideoService {

    // 按照栏目类别ID并且status = 1
    String selectVideoByCategoryId(String locale,Integer categoryId,Integer currentPage,Integer pageSize);

    // 查询channel为1的，parent_id为0的栏目类别
    List<SkChannelArticleVideoCn> selectCategory(String locale, Integer channelId);

    // 查询单条新闻
    SkChannelArticleVideoCn selectByPrimaryKeyInfo(Integer id, String locale);
}

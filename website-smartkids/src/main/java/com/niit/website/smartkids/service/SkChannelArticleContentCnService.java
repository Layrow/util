package com.niit.website.smartkids.service;


import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleContentCn;

import java.util.List;

public interface SkChannelArticleContentCnService {

    String selectContentByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize);

    List<SkArticleCategoryCn> selectCategory(String locale, Integer channelId);

    SkChannelArticleContentCn selectByPrimaryKeyInfo(Integer id, String locale);

}


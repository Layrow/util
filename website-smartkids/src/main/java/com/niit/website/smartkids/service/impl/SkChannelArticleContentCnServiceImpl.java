package com.niit.website.smartkids.service.impl;


import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleContentCn;
import com.niit.website.smartkids.service.SkChannelArticleContentCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SkChannelArticleContentCnServiceImpl implements SkChannelArticleContentCnService {


    final String SERVICE_NAME = "service-cms";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String selectContentByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/content/{locale}/{categoryId}/?currentPage=" + currentPage + "&pageSize=" + pageSize,
                String.class, locale, categoryId);
    }

    @Override
    public List<SkArticleCategoryCn> selectCategory(String locale, Integer channelId) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory?locale=" + locale + "&channelId=" + channelId,
                List.class, locale);
    }

    @Override
    public SkChannelArticleContentCn selectByPrimaryKeyInfo(Integer id, String locale) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/content/id?id=" + id + "&locale=" + locale,
                SkChannelArticleContentCn.class);
    }
}

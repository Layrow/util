package com.niit.website.smartkids.service.impl;


import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleNewsCn;
import com.niit.website.smartkids.service.SkChannelArticleNewsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SkChannelArticleNewsCnServiceImpl implements SkChannelArticleNewsCnService {

    final String SERVICE_NAME = "service-cms";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String selectNewsByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/news/{locale}/{categoryId}/?currentPage=" + currentPage+"&pageSize=" +pageSize,
                String.class,locale,categoryId);
    }

    @Override
    public List<SkArticleCategoryCn> selectCategory(String locale,Integer channelId) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory?locale=" + locale+"&channelId=" + channelId,
                List.class,locale);
    }

    @Override
    public SkChannelArticleNewsCn selectByPrimaryKeyInfo(Integer id, String locale) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/news/id?id=" + id+"&locale=" + locale,
                SkChannelArticleNewsCn.class);
    }
}

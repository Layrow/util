package com.niit.website.cms.service.impl;

import com.github.pagehelper.PageInfo;
import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkChannelArticleNewsCn;
import com.niit.website.cms.service.SkChannelArticleNewsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class SkChannelArticleNewsCnServiceImpl implements SkChannelArticleNewsCnService {
    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;
    @Override
    public PageInfo<SkChannelArticleNewsCn> likeSelectAll(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/news/titles?categoryId="+categoryId+"&key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }
    @Override
    public int insertSelective(SkChannelArticleNewsCn record,String locale) {
        BaseResult<String> result = restTemplate.postForObject("http://" + SERVICE_NAME + "/news?locale="+locale, record, BaseResult.class);
        return 1;
    }
    @Override
    public String batchUp(List<SkChannelArticleNewsCn> lis, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news/batch?locale="+locale,lis);
        return "保存审核";
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleNewsCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news?locale="+locale,record);
        return 1;
    }
    @Override
    public PageInfo<SkChannelArticleNewsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/news?key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale, PageInfo.class);
    }

    @Override
    public PageInfo<SkChannelArticleNewsCn> selectAll(int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/news?currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale,PageInfo.class);
    }

    @Override
    public void deleteAd(String id,String locale) {
        restTemplate.delete("http://" + SERVICE_NAME + "/news?id="+id+"&locale="+locale,id,locale);
    }

   /* @Override
    public PageInfo<SkChannelArticleNewsCn> likeSelectAll(int currentPage, int pageSize, String title,String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/news/titles?currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }*/

    @Override
    public void updateSt(String id,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news/review?id="+id+"&locale="+locale,id,locale);
    }

    @Override
    public void updateTo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news/top?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateRe(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news/red?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateHo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/news/hot?id="+id+"&locale="+locale,id,locale);


    }
}

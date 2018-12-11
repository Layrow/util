package com.niit.website.cms.service.impl;


import com.github.pagehelper.PageInfo;
import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkChannelArticleVideoCn;
import com.niit.website.cms.service.SkChannelArticleVideoCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class SkChannelArticleVideoCnServiceImpl  implements SkChannelArticleVideoCnService {
    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;
    @Override
    public PageInfo<SkChannelArticleVideoCn> likeSelectAll(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/video/titles?categoryId="+categoryId+"&key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }
    @Override
    public String batchUp(List<SkChannelArticleVideoCn> lis, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video/batch?locale="+locale,lis);
        return "保存审核";
    }

    @Override
    public int insertSelective(SkChannelArticleVideoCn record,String locale) {
        BaseResult<String> result = restTemplate.postForObject("http://" + SERVICE_NAME + "/video?locale="+locale, record, BaseResult.class);
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleVideoCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video?locale="+locale,record);
        return 1;
    }

    @Override
    public PageInfo<SkChannelArticleVideoCn> selectAll(int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/video?currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale,PageInfo.class);
    }

    @Override
    public PageInfo<SkChannelArticleVideoCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/video?key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale, PageInfo.class);
    }

   /* @Override
    public PageInfo<SkChannelArticleVideoCn> likeSelectAll(int currentPage, int pageSize, String title,String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/video/titles?currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }*/


    @Override
    public void deleteAd(String id,String locale) {

        restTemplate.delete("http://" + SERVICE_NAME + "/video?id="+id+"&locale="+locale,id,locale);
    }

    @Override
    public void updateSt(String id,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video/review?id="+id+"&locale="+locale,id,locale);

    }

    @Override
    public void updateTo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video/top?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateRe(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video/red?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateHo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/video/hot?id="+id+"&locale="+locale,id,locale);


    }

}

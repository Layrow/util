package com.niit.website.cms.service.impl;


import com.github.pagehelper.PageInfo;
import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkChannelArticleGoodsCn;
import com.niit.website.cms.service.SkChannelArticleGoodsCnService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class SkChannelArticleGoodsCnServiceImpl  implements SkChannelArticleGoodsCnService {
    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;
    @Override
    public int insertSelective(SkChannelArticleGoodsCn record,String locale) {
        BaseResult<String> result = restTemplate.postForObject("http://" + SERVICE_NAME + "/goods?locale="+locale, record, BaseResult.class);
        return 1;
    }
    @Override
    public String batchUp(List<SkChannelArticleGoodsCn> lis, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods/batch?locale="+locale,lis);
        return "保存审核";
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleGoodsCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods?locale="+locale,record);
        return 1;
    }
    @Override
    public PageInfo<SkChannelArticleGoodsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/goods?key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale, PageInfo.class);
    }

    @Override
    public PageInfo<SkChannelArticleGoodsCn> selectAll(int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/goods?currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale,PageInfo.class);
    }

    @Override
    public void deleteAd(String id,String locale) {
        restTemplate.delete("http://" + SERVICE_NAME + "/goods?id="+id+"&locale="+locale,id,locale);
    }



    @Override
    public PageInfo<SkChannelArticleGoodsCn> likeSelectAll(Integer categoryId, String key,int currentPage, int pageSize, String title,String locale,Integer channelId) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/goods/titles?categoryId="+categoryId+"&key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale+"&channelId="+channelId,null,PageInfo.class);
    }

    @Override
    public void updateSt(String id,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods/review?id="+id+"&locale="+locale,id,locale);

    }

    @Override
    public void updateTo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods/top?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateRe(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods/red?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateHo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/goods/hot?id="+id+"&locale="+locale,id,locale);


    }

}

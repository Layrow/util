package com.niit.website.cms.service.impl;


import com.github.pagehelper.PageInfo;
import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkChannelArticleContentCn;
import com.niit.website.cms.service.SkChannelArticleContentCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SkChannelArticleContentCnServiceImpl implements SkChannelArticleContentCnService {
    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;
    @Override
    public PageInfo<SkChannelArticleContentCn> likeSelectAll(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/content/titles?categoryId="+categoryId+"&key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }

    @Override
    public int insertSelective(SkChannelArticleContentCn record,String locale) {
        BaseResult<String> result = restTemplate.postForObject("http://" + SERVICE_NAME + "/content?locale="+locale, record, BaseResult.class);
        return 1;
    }
    @Override
    public String batchUp(List<SkChannelArticleContentCn> lis, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content/batch?locale="+locale,lis);
        return "保存审核";
    }
    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleContentCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content?locale="+locale,record);
        return 1;
    }

//    @Override
//    public PageInfo<SkChannelArticleContentCn> selectAll(int currentPage, int pageSize) {
//        return restTemplate.getForObject("http://" + SERVICE_NAME + "/content?currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class);
//    }


    @Override
    public PageInfo<SkChannelArticleContentCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/content?key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale, PageInfo.class);

    }

    @Override
    public void deleteAd(String id,String locale) {
        restTemplate.delete("http://" + SERVICE_NAME + "/content?id="+id+"&locale="+locale,id,locale);
    }

  /*  @Override
    public PageInfo<SkChannelArticleContentCn> likeSelectAll(int currentPage, int pageSize, String title,String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "/content/titles?currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
    }*/

    @Override
    public void updateSt(String id,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content/review?id="+id+"&locale="+locale,id,locale);

    }

    @Override
    public void updateTo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content/top?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateRe(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content/red?id="+id+"&locale="+locale,id,locale);


    }

    @Override
    public void updateHo(String id, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/content/hot?id="+id+"&locale="+locale,id,locale);


    }

}

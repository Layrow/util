package com.niit.website.smartkids.service.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsCn;
import com.niit.website.smartkids.service.SkchannelArticleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.23 09:55
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.23 09:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@Service
public class SkchannelArticleGoodsServiceImpl implements SkchannelArticleGoodsService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;


    @Override
    public String easyLikeSelectAll(String keyword,Integer channelId, String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/goods/main_category_fuzz_search?keyword="+keyword+"&channelId="+channelId+"&locale="+locale,String.class);
    }

    @Override
    public String getArticleCountByCategory(Integer channelId, String locale,String keyword) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/goods/main_category_article_count?channelId="+channelId+"&locale="+locale+"&keyword="+keyword,String.class);
    }

    @Override
    public PageInfo<SkChannelArticleGoodsCn> getByCategory(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale, Integer channelId,String orderBy) {
         return restTemplate.postForObject("http://" + SERVICE_NAME + "/goods/titles?categoryId="+categoryId+"&key="+key+"&currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale+"&channelId="+channelId+"&orderBy="+orderBy,null,PageInfo.class);
    }
}

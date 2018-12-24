package com.niit.website.smartkids.service.impl;

import com.niit.website.smartkids.pojo.SkChannelArticleVideoCn;
import com.niit.website.smartkids.service.SkchannelArticleVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
public class SkchannelArticleVideoServiceImpl implements SkchannelArticleVideoService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String selectVideoByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/video/{locale}/{categoryId}/?currentPage=" + currentPage+"&pageSize=" +pageSize,
                String.class,locale,categoryId);
    }

    @Override
    public List<SkChannelArticleVideoCn> selectCategory(String locale, Integer channelId) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory?locale=" + locale+"&channelId=" + channelId,
                List.class,locale);
    }

    @Override
    public SkChannelArticleVideoCn selectByPrimaryKeyInfo(Integer id, String locale) {
        return restTemplate.getForObject(
                "http://" + SERVICE_NAME + "/video/id?id=" + id+"&locale=" + locale,
                SkChannelArticleVideoCn.class);
    }

//    @Override
//    public Integer getByCategory(String key,Integer pageSize,Integer currentPage,Integer categoryId) {
////        restTemplate.postForObject("http://" + SERVICE_NAME + "/link?locale=" + locale,Integer.class);
//        return 0;
//    }
}

package com.niit.website.smartkids.service.impl;

import com.niit.website.smartkids.service.SkchannelArticleVideoService;
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
public class SkchannelArticleVideoServiceImpl implements SkchannelArticleVideoService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;

//    @Override
//    public Integer getByCategory(String key,Integer pageSize,Integer currentPage,Integer categoryId) {
////        restTemplate.postForObject("http://" + SERVICE_NAME + "/link?locale=" + locale,Integer.class);
//        return 0;
//    }
}

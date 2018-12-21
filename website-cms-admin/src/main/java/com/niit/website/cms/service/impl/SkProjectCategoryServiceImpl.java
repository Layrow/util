package com.niit.website.cms.service.impl;

import com.niit.website.cms.pojo.SkProjectCategory;
import com.niit.website.cms.service.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SkProjectCategoryServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCategoryServiceImpl implements SkProjectCategoryService {

    final String SERVICE_NAME = "service-project";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/projectcategory/{id}",id);
    }

    @Override
    public void insert(SkProjectCategory record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/projectcategory", record,String.class);
    }

    @Override
    public SkProjectCategory selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/projectcategory/{id}",SkProjectCategory.class,id);
    }

    @Override
    public void updateByPrimaryKey(SkProjectCategory record) {
        restTemplate.put("http://" + SERVICE_NAME + "/projectcategory",record);
    }
}

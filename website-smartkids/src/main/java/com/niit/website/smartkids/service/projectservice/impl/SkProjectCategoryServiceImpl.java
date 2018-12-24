package com.niit.website.smartkids.service.projectservice.impl;

import com.niit.website.smartkids.pojo.project.SkProjectCategory;
import com.niit.website.smartkids.service.projectservice.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    // delete
    @Override
    public void deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/projectcategory/{id}",id);
    }

    // insert
    @Override
    public void insert(SkProjectCategory record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/projectcategory", record,String.class);
    }

    // select
    @Override
    public SkProjectCategory selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/projectcategory/{id}",SkProjectCategory.class,id);
    }

    // update
    @Override
    public void updateByPrimaryKey(SkProjectCategory record) {
        restTemplate.put("http://" + SERVICE_NAME + "/projectcategory",record);
    }

    // 查询所有作品栏目分类
    @Override
    public List<SkProjectCategory> selectAllProjectCategory() {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/projectcategory/all",List.class);
    }
}

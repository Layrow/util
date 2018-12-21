package com.niit.website.cms.service.impl;

import com.niit.website.cms.pojo.SkProjectComments;
import com.niit.website.cms.service.SkProjectCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SkProjectCommentsServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCommentsServiceImpl implements SkProjectCommentsService {

    final String SERVICE_NAME = "service-project";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/projectcomments/{id}",id);
    }

    @Override
    public void insert(SkProjectComments record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/projectcomments", record,String.class);
    }

    @Override
    public SkProjectComments selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/projectcomments/{id}", SkProjectComments.class, id);
    }

    @Override
    public void updateByPrimaryKey(SkProjectComments record) {
        restTemplate.put("http://" + SERVICE_NAME + "/projectcomments",record);
    }

    @Override
    public void updateMoreProjectComment(String id) {
        restTemplate.put("http://" + SERVICE_NAME + "/projectcomments/more?id=" + id,id);
    }

    @Override
    public void deleteMoreProjectComment(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/projectcomments/more?id="+id,id);

    }
}

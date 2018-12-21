package com.niit.website.smartkids.service.projectservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.niit.website.smartkids.service.projectservice.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SkProjectServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectServiceImpl implements SkProjectService {

    final String SERVICE_NAME = "service-project";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int insert(SkProject record) {
        return 1;
//        return restTemplate.postForObject("http://" + SERVICE_NAME + "/goods?id="+id+"&locale="+locale,id,locale);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public SkProject selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SkProject record) {
        return 0;
    }

    @Override
    public int deleteMoreProject(String id) {
        return 0;
    }

    @Override
    public PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize) {
        return null;
    }

    @Override
    public Integer updateMoreProject(String id) {
        return null;
    }

    @Override
    public PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize) {
        return null;
    }
}

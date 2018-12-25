package com.niit.website.cms.service.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkProject;
import com.niit.website.cms.service.SkProjectService;
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

    // 上传作品
    @Override
    public void insert(SkProject record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/project",record,String.class);
    }

    // 删除作品
    @Override
    public void deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/project?id=" + id,id);
    }

    // 查找作品详情
    @Override
    public SkProject selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/{id}",SkProject.class,id);
    }

    // 修改作品内容
    @Override
    public void updateByPrimaryKey(SkProject record) {
        restTemplate.put("http://" + SERVICE_NAME + "/project",record);
    }

    // 批量删除作品
    @Override
    public void deleteMoreProject(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/project?id=" +id,id);
    }

    // 分页查找所有作品，状态码可选
    @Override
    public PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/more?currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);

        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/more?currentPage=" + currentPage + "&status="+status+"&pageSize=" + pageSize, PageInfo.class);
    }

    // 批量更改状态码
    @Override
    public void updateMoreProject(String sign,String id) {
        restTemplate.put("http://" + SERVICE_NAME + "/project/more?id="+id+"&sign="+sign,id);
    }

    // 根据title，status分页查询作品
    @Override
    public PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/title?currentPage="
                +currentPage + "&pageSize="+pageSize + "&status=" + status + "&title=" +title,PageInfo.class);
    }

    @Override
    public PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/title?currentPage="
                +currentPage + "&pageSize="+pageSize + "&status=" + status + "&title=" +title + "&categoryId=" +categoryId + "&orderBy=" + orderBy,PageInfo.class);
    }

    // 查询某用户下的作品
    @Override
    public PageInfo<SkProject> selectProjectByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/user?currentPage="
                +currentPage + "&pageSize="+pageSize + "&userId=" + userId,PageInfo.class);
    }
}

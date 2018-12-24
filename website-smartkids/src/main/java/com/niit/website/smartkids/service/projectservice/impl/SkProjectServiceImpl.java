package com.niit.website.smartkids.service.projectservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.enums.IntegralActionsEnum;
import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationOps;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.niit.website.smartkids.service.projectservice.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    final String SERVICE_MEMVER = "service-member";
    @Autowired
    private RestTemplate restTemplate;

    // 上传作品
    @Override
    @Transactional
    public void insert(SkProject record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/project",record,String.class);
        // 积分管理
        SkMemberIntegral integral = new SkMemberIntegral();
        integral.setActions(IntegralActionsEnum.POST_PROJECT.getAction());
        integral.setNumbers(IntegralActionsEnum.POST_PROJECT.getNums());
        integral.setOperation(IntegralActionsEnum.POST_PROJECT.getOperation());
        integral.setUserId(record.getUserId());
        integral.setUserName(record.getUserName());
        // 积分变更
        restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral",integral,String.class);
        // 积分变更通知
        SkMemberNotificationOps ops = new SkMemberNotificationOps();
        ops.setOperation(IntegralActionsEnum.POST_PROJECT.getOperation());
        ops.setProjectId(record.getId());
        ops.setUserId(record.getUserId());
        ops.setUserName(record.getUserName());
        restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add",ops,String.class);

    }

    // 删除作品
    @Override
    @Transactional
    public void deleteByPrimaryKey(Integer id, Integer userId, String userName) {
        // 删除作品
        restTemplate.delete("http://" + SERVICE_NAME + "/project?id=" + id,id);
        SkMemberIntegral integral = new SkMemberIntegral();
    }

    // TODO 重复点赞，积分变更，通知
    @Override
    public SkProject selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/{id}",SkProject.class,id);
    }

    @Override
    public void updateByPrimaryKey(SkProject record) {
        restTemplate.put("http://" + SERVICE_NAME + "/project",record);
    }

    @Override
    public void deleteMoreProject(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/project/{id}",id);
    }

    @Override
    public PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/more?currentPage=" + currentPage + "&pageSize=" + pageSize + "&status="+status, PageInfo.class);
    }

    @Override
    public void updateMoreProject(String id) {
        restTemplate.put("http://" + SERVICE_NAME + "/project/more?id="+id,id);
    }

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

    @Override
    public PageInfo<SkProject> selectProjectByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/user?currentPage="
                +currentPage + "&pageSize="+pageSize + "&userId=" + userId,PageInfo.class);
    }
}

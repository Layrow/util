package com.niit.website.smartkids.service.projectservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.enums.IntegralActionsEnum;
import com.niit.website.smartkids.enums.NotificationEnum;
import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationOps;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.niit.website.smartkids.service.projectservice.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
    @Transactional(rollbackFor=Exception.class)
    public void insert(SkProject record) {
        String id = restTemplate.postForObject("http://" + SERVICE_NAME + "/project", record,String.class);
        // 积分变更通知
        SkMemberNotificationOps ops = new SkMemberNotificationOps();
        ops.setOperation(NotificationEnum.PURCHASE.getOperation());
        ops.setNoticeTime(new Date());
        ops.setProjectId(Integer.valueOf(id));
        ops.setUserId(record.getUserId());
        ops.setUserName(record.getUserName());
        String s = restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add", ops, String.class);
        if ("true".equals(s)) {
            // 积分管理
            SkMemberIntegral integral = new SkMemberIntegral();
            integral.setActions(IntegralActionsEnum.POST_PROJECT.getAction());
            integral.setNumbers(IntegralActionsEnum.POST_PROJECT.getNums());
            integral.setOperation(IntegralActionsEnum.POST_PROJECT.getOperation());
            integral.setUserId(record.getUserId());
            integral.setUserName(record.getUserName());
            // 积分变更
            restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral",integral,String.class);
        }
    }

    // 删除作品
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteByPrimaryKey(Integer id) {
        // 删除作品
        restTemplate.delete("http://" + SERVICE_NAME + "/project?id=" + id,id);
    }

    @Override
    public SkProject selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/{id}",SkProject.class,id);
    }

    // update
    /**
     * @Description record-作品实体，userlikeId-谁（用户ID）点赞了该作品，userlikeName点赞了该作品
     * @Date 2018/12/25 11:02
     * @Param [record, userlikeId, userlikeName]
     * @Return void
     **/
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateByPrimaryKey(SkProject record,Integer userlikeId,String userlikeName) {
        SkProject skProject = selectByPrimaryKey(record.getId());
        // 点赞
        if(record.getLikesCount() > skProject.getLikesCount()) {

            // 积分变更通知
            SkMemberNotificationOps ops = new SkMemberNotificationOps();
            ops.setOperation(NotificationEnum.LIKE.getOperation());
            ops.setProjectId(record.getId());
            ops.setUserId(userlikeId);
            ops.setUserName(userlikeName);
            String s = restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add",ops,String.class);
            System.out.println(Boolean.getBoolean(s));
            // 如果通知成功，进行积分变更
            if ("true".equals(s)) {
                // 积分管理
                SkMemberIntegral integral = new SkMemberIntegral();
                integral.setActions(IntegralActionsEnum.POST_LIKE.getAction());
                integral.setNumbers(IntegralActionsEnum.POST_LIKE.getNums());
                integral.setOperation(IntegralActionsEnum.POST_LIKE.getOperation());
                integral.setUserId(record.getUserId());
                integral.setUserName(record.getUserName());
                // 积分变更
                restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral",integral,String.class);
                // 积分通知不成功，积分不变更，作品内容不更新
                restTemplate.put("http://" + SERVICE_NAME + "/project",record);
            }
        } else if (record.getFavCount() > skProject.getFavCount()) {

            // 收藏
            // 积分变更通知
            SkMemberNotificationOps ops = new SkMemberNotificationOps();
            ops.setOperation(NotificationEnum.COLLECT.getOperation());
            ops.setProjectId(record.getId());
            ops.setUserId(userlikeId);
            ops.setUserName(userlikeName);
            String s = restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add",ops,String.class);
            // 如果通知成功，进行积分变更
            if ("true".equals(s)) {
                // 积分管理
                SkMemberIntegral integral = new SkMemberIntegral();
                integral.setActions(IntegralActionsEnum.POST_COLLECT.getAction());
                integral.setNumbers(IntegralActionsEnum.POST_COLLECT.getNums());
                integral.setOperation(IntegralActionsEnum.POST_COLLECT.getOperation());
                integral.setUserId(record.getUserId());
                integral.setUserName(record.getUserName());
                // 积分变更
                restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral",integral,String.class);
                // 作品信息变更
                restTemplate.put("http://" + SERVICE_NAME + "/project",record);

            }
        } else {
            restTemplate.put("http://" + SERVICE_NAME + "/project",record);
        }

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
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/type?currentPage="
                +currentPage + "&pageSize="+pageSize + "&status=" + status + "&title=" +title + "&categoryId=" +categoryId + "&orderBy=" + orderBy,PageInfo.class);
    }

    @Override
    public PageInfo<SkProject> selectProjectByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/user?currentPage="
                +currentPage + "&pageSize="+pageSize + "&userId=" + userId,PageInfo.class);
    }
}

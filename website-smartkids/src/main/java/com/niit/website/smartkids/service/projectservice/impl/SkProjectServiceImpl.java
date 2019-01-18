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
import java.util.List;

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

    /**
     * 上传作品，变更积分
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProject(SkProject record) {
        String id = restTemplate.postForObject("http://" + SERVICE_NAME + "/project", record, String.class);
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
            restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral", integral, String.class);
        }
    }


    /**
     * 删除作品
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/project?id=" + id, id);
    }

    @Override
    public String selectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/{id}", String.class, id);
    }

    @Override
    public SkProject selectProjectByPrimaryKey(Integer id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/info/{id}", SkProject.class, id);
    }

    @Override
    public List selectProjectOperation(Integer userId, Integer projectId) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/user_operation?user_id=" + userId + "&project_id=" + projectId
                , List.class);
    }

    /**
     * @Description record-作品实体，userlikeId-哪位用户（用户ID）点赞了该作品，
     * userlikeName点赞了该作品，operate是执行的那个更新操作
     * @Date 2018/12/25 11:02
     * @Param [record, userlikeId, userlikeName]
     * @Return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKey(SkProject record, Integer operationUserId, String operationUserName, String operate) {

        // 获得该作品在数据库中的信息
        SkProject skProject = selectProjectByPrimaryKey(record.getId());
        switch (operate) {
            // 点赞请求 - 积分变更通知 - 积分变更
            case "addLikeCount":
                // 发送积分变更 通知
                SkMemberNotificationOps ops = new SkMemberNotificationOps();
                ops.setOperation(NotificationEnum.LIKE.getOperation());
                ops.setProjectId(record.getId());
                ops.setUserId(operationUserId);
                ops.setUserName(operationUserName);
                String s = restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add", ops, String.class);

                // 如果通知成功，进行积分变更
                if ("true".equals(s)) {
                    // 封装积分对象
                    SkMemberIntegral integral = new SkMemberIntegral();
                    integral.setActions(IntegralActionsEnum.POST_LIKE.getAction());
                    integral.setNumbers(IntegralActionsEnum.POST_LIKE.getNums());
                    integral.setOperation(IntegralActionsEnum.POST_LIKE.getOperation());
                    integral.setUserId(record.getUserId());
                    integral.setUserName(record.getUserName());
                    // 积分变更
                    restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral", integral, String.class);
                    // 点赞数加一
                    record.setLikesCount(skProject.getLikesCount() + 1);
                    // 更新作品信息
                    restTemplate.put("http://" + SERVICE_NAME + "/project", record);
                }
                break;
            // 收藏请求
            case "addFavCount":
                // 发送 积分变更通知
                SkMemberNotificationOps ops1 = new SkMemberNotificationOps();
                ops1.setOperation(NotificationEnum.COLLECT.getOperation());
                ops1.setProjectId(record.getId());
                ops1.setUserId(operationUserId);
                ops1.setUserName(operationUserName);
                String s1 = restTemplate.postForObject("http://" + SERVICE_MEMVER + "/notification/add", ops1, String.class);
                // 如果通知成功 - 进行积分变更
                if ("true".equals(s1)) {
                    // 积分管理
                    SkMemberIntegral integral = new SkMemberIntegral();
                    integral.setActions(IntegralActionsEnum.POST_COLLECT.getAction());
                    integral.setNumbers(IntegralActionsEnum.POST_COLLECT.getNums());
                    integral.setOperation(IntegralActionsEnum.POST_COLLECT.getOperation());
                    integral.setUserId(record.getUserId());
                    integral.setUserName(record.getUserName());
                    // 积分变更
                    restTemplate.postForObject("http://" + SERVICE_MEMVER + "/integral/integral", integral, String.class);
                    // 收藏数加一
                    record.setFavCount(skProject.getFavCount() + 1);
                    // 作品信息变更
                    restTemplate.put("http://" + SERVICE_NAME + "/project", record);
                }
                break;
            // 取消点赞
            case "subLikeCount":
                record.setLikesCount(skProject.getLikesCount() - 1);
                int operation = NotificationEnum.LIKE.getOperation();
                restTemplate.put("http://" + SERVICE_NAME + "/project", record);
                restTemplate.delete("http://" + SERVICE_MEMVER + "/notification/delnotification?userId=" + operationUserId + "&projectId=" + record.getId() + "&operation=" + operation
                        , operationUserId, record.getId());
                break;
            // 取消收藏
            case "subFavCount":
                record.setFavCount(skProject.getFavCount() - 1);
                int collectOperation = NotificationEnum.COLLECT.getOperation();
                restTemplate.put("http://" + SERVICE_NAME + "/project", record);
                restTemplate.delete("http://" + SERVICE_MEMVER + "/notification/delnotification?userId=" + operationUserId + "&projectId=" + record.getId() + "&operation=" + collectOperation
                        , operationUserId, record.getId());
                break;
            default:
                restTemplate.put("http://" + SERVICE_NAME + "/project", record);

        }

    }

    @Override
    public void deleteMoreProject(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/project/{id}", id);
    }

    @Override
    public PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/more?currentPage=" + currentPage + "&pageSize=" + pageSize + "&status=" + status, PageInfo.class);
    }

    @Override
    public void updateMoreProject(String id) {
        restTemplate.put("http://" + SERVICE_NAME + "/project/more?id=" + id, id);
    }

    @Override
    public PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/title?currentPage="
                + currentPage + "&pageSize=" + pageSize + "&status=" + status + "&title=" + title, PageInfo.class);
    }

    @Override
    public PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/type?currentPage="
                + currentPage + "&pageSize=" + pageSize + "&status=" + status + "&title=" + title + "&categoryId=" + categoryId + "&orderBy=" + orderBy, PageInfo.class);
    }

    @Override
    public String selectProjectByUserId(Integer userId) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/project/user?userId=" + userId, String.class);
    }
}

package com.niit.website.bbs.service.impl;

import com.github.pagehelper.PageInfo;
import com.niit.common.entity.BaseResult;
import com.niit.website.bbs.pojo.SkBbsReply;
import com.niit.website.bbs.service.SkBbsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date:2018/12/06 10:57
 * @Description:
 */
@Service
public class SkBbsReplyServiceImpl implements SkBbsReplyService {
    final String SERVICE_NAME = "service-BBS";
    @Autowired
    RestTemplate restTemplate;


    @Override
    public String replyInfo(Integer sectionId) {
        return restTemplate.postForObject("http://" + SERVICE_NAME +
                "/reply/replyInfo?sectionId="+sectionId,null,String.class);
    }

    @Override
    public int insertSelective(SkBbsReply record) {
        BaseResult baseResult = restTemplate.postForObject("http://" + SERVICE_NAME + "/reply", record, BaseResult.class);
        return 1;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
       // restTemplate.delete("http://"+SERVICE_NAME+"/reply");
        return 0;
    }

    @Override
    public PageInfo<SkBbsReply> selectReply(Integer currentPage, Integer pageSize, String replyUserId) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/reply?currentPage="+currentPage+"&pageSize="+pageSize+"&replyUserId="+replyUserId,PageInfo.class);
    }
    @Override
    public String selectCount(Integer topicId) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/count?topicId="+topicId,String.class);
    }

    @Override
    public PageInfo<SkBbsReply> selectAllReply(Integer topicId, Integer currentPage, Integer pageSize) {
       return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply?topicId="+topicId+"&currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class);

    }

    @Override
    public Map<String, Object> selectDate(Integer topicId) {
        Map map = restTemplate.getForObject("http://" + SERVICE_NAME + "/reply/date?topicId=" + topicId, Map.class);
        return map;
    }

    @Override
    public void deleteAd(String id) {
        restTemplate.delete("http://"+SERVICE_NAME+"/reply/batch?id="+id);

    }

    @Override
    public PageInfo<SkBbsReply> selectAll(Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/all?currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class);
    }

    @Override
    public int updateByPrimaryKeySelective(SkBbsReply record) {
         restTemplate.put("http://"+SERVICE_NAME+"/reply",record);
         return  1;
    }
    @Override
    public void updateAd(String id) {
        restTemplate.put("http://"+SERVICE_NAME+"/reply/batch?id="+id,id);

    }

    @Override
    public PageInfo<SkBbsReply> selectAllStatus(Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/status?currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class);
    }

    @Override
    public PageInfo<SkBbsReply> selectAllNoStatus(Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/NoStatus?currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class);
    }

    @Override
    public String selectAll() {
        return  restTemplate.getForObject("http://"+SERVICE_NAME+"/reply/replyAll",String.class);
    }

}

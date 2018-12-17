package com.niit.service.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.BadWordUtil;
import com.niit.common.utils.Tools;
import com.niit.service.bbs.dao.SkBbsReplyMapper;
import com.niit.service.bbs.dao.SkBbsTopicMapper;
import com.niit.service.bbs.pojo.SkBbsTopic;
import com.niit.service.bbs.service.SkBbsReplyService;
import com.niit.service.bbs.pojo.SkBbsReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: huangwei
 * @Date:2018/12/04 10:53
 * @Description:
 */
@Service
public class SkBbsReplyServiceImpl implements SkBbsReplyService {
    @Resource
    private SkBbsReplyMapper skBbsReplyMapper;
    @Resource
    private SkBbsTopicMapper skBbsTopicMapper;

    @Override
    public int insertSelective(SkBbsReply record) {

        if (BadWordUtil.isContaintBadWord(record.getContent(), 2)){
            record.setContent(BadWordUtil.replaceBadWord(record.getContent(),2,"*"));
        }
        record.setReplyTime(new Date());
        record.setReplyStatus(1);
        return skBbsReplyMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skBbsReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String selectCount(Integer topicId) {

        return skBbsReplyMapper.selectCount(topicId);

    }
    @Override
    public PageInfo<SkBbsReply> selectReply(Integer currentPage, Integer pageSize,String replyUserId) {
        PageInfo<SkBbsReply>  pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectReply(replyUserId);
        pageInfo = new PageInfo<>(skBbsReplies);
        return pageInfo;
    }

/*    @Override
    public Map<Integer, List<Object>> selectReplyUserId(Integer currentPage, Integer pageSize, String replyUserId) {
        return null;
    }*/
/*

    @Override
    public Map<Integer, List<Object>> selectReplyUserId(Integer currentPage, Integer pageSize,String replyUserId) {
        List<SkBbsTopic> list;
        PageHelper.startPage(currentPage,pageSize);
        //帖子列表 并分页查询
        list=skBbsTopicMapper.listAllTopicInSection(sectionId);

        //把所有帖子的ID放入topicIds
        List<Integer> topicIds=new LinkedList<>();
        list.stream().forEach(e->topicIds.add(e.getId()));
        List<SkBbsReply> replys = skBbsReplyMapper.selectReplyUserId(replyUserId);
        Map<Integer, List<Object>> totalResult=new LinkedHashMap<>();
        list.stream().forEach(e->{
            //tempList用于存放回复总量、最新回复
            List<Object> tempList=new LinkedList<>();
            //找到与帖子ID对应的所有回复
            List<SkBbsReply> matchedReplys=replys.stream().filter(p->p.getTopicId().equals(e.getId())).collect(Collectors.toList());
            //运算出回复总量
            Integer replysCount=matchedReplys.size();
            //运算出最新回复实体
            SkBbsReply lastedReply;
            if(replysCount>0) {
                lastedReply= matchedReplys.stream().sorted(Comparator.comparing(SkBbsReply::getReplyTime).reversed()).findFirst().get();
            }else{
                lastedReply=null;
            }
            //把回复总量、最新回复加入tempList
            tempList.add(replysCount);
            tempList.add(lastedReply);
            //把tempList放入结果集，key是topicId
            totalResult.put(e.getId(),tempList);
        });
        return totalResult;

    }
*/


    @Override
    public Map<Integer, List<Object>> replyInfo(Integer currentPage, Integer pageSize, Integer sectionId) {
        List<SkBbsTopic> list;
        PageHelper.startPage(currentPage,pageSize);
        //帖子列表 并分页查询
        list=skBbsTopicMapper.listAllTopicInSection(sectionId);
        //把所有帖子的ID放入topicIds
        List<Integer> topicIds=new LinkedList<>();
        list.stream().forEach(e->topicIds.add(e.getId()));
        //根据topicIds查询相关所有回复
        List<SkBbsReply> replys=skBbsReplyMapper.selectReplyByIds(topicIds);
//        List<SkBbsReply> replys=skBbsReplyMapper.selectAll();
        //totalResult是最后的结果集，key是帖子ID，value是下面的tempList（存放回复总量、最新回复）
        Map<Integer, List<Object>> totalResult=new LinkedHashMap<>();
        list.stream().forEach(e->{
            //tempList用于存放回复总量、最新回复
            List<Object> tempList=new LinkedList<>();
            //找到与帖子ID对应的所有回复
            List<SkBbsReply> matchedReplys=replys.stream().filter(p->p.getTopicId().equals(e.getId())).collect(Collectors.toList());
            //运算出回复总量
            Integer replysCount=matchedReplys.size();
            //运算出最新回复实体
            SkBbsReply lastedReply;
            if(replysCount>0) {
                lastedReply= matchedReplys.stream().sorted(Comparator.comparing(SkBbsReply::getReplyTime).reversed()).findFirst().get();
            }else{
                lastedReply=null;
            }
            //把回复总量、最新回复加入tempList
            tempList.add(replysCount);
            tempList.add(lastedReply);
            //把tempList放入结果集，key是topicId
            totalResult.put(e.getId(),tempList);
        });
        return totalResult;

    }

    @Override
    public Map<Integer, List<Object>> selectAll() {
        List<SkBbsTopic> list;
        list = skBbsTopicMapper.listAllTopic();
        List<Integer> topicIds=new LinkedList<>();
        list.stream().forEach(e->topicIds.add(e.getId()));
        //根据topicIds查询相关所有回复
        List<SkBbsReply> replys=skBbsReplyMapper.selectReplyByIds(topicIds);
//        List<SkBbsReply> replys=skBbsReplyMapper.selectAll();
        //totalResult是最后的结果集，key是帖子ID，value是下面的tempList（存放回复总量、最新回复）
        Map<Integer, List<Object>> totalResult=new LinkedHashMap<>();
        list.stream().forEach(e->{
            //tempList用于存放回复总量、最新回复
            List<Object> tempList=new LinkedList<>();
            //找到与帖子ID对应的所有回复
            List<SkBbsReply> matchedReplys=replys.stream().filter(p->p.getTopicId().equals(e.getId())).collect(Collectors.toList());
            //运算出回复总量
            Integer replysCount=matchedReplys.size();
            //运算出最新回复实体
            SkBbsReply lastedReply;
            if(replysCount>0) {
                lastedReply= matchedReplys.stream().sorted(Comparator.comparing(SkBbsReply::getReplyTime).reversed()).findFirst().get();
            }else{
                lastedReply=null;
            }
            //把回复总量、最新回复加入tempList
            tempList.add(replysCount);
            tempList.add(lastedReply);
            //把tempList放入结果集，key是topicId
            totalResult.put(e.getId(),tempList);
        });
        return totalResult;

    }


    @Override
    public PageInfo<SkBbsReply> selectAllReply(Integer topicId, Integer currentPage, Integer pageSize) {
        PageInfo<SkBbsReply> pageInfo =null;
        PageHelper.startPage(currentPage,pageSize);
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectAllReply(topicId);
        pageInfo = new PageInfo<>(skBbsReplies);
        return pageInfo;
    }

    @Override
    public Map<String,Object> selectDate(Integer topicId) {
        Map<String, Object> map = skBbsReplyMapper.selectDate(topicId);
        return map;
    }

    @Override
    public void deleteAd(String id) {
        List<String> list = Tools.getList(id);
        skBbsReplyMapper.deleteAd(list);
    }

    @Override
    public PageInfo<SkBbsReply> selectAll(Integer currentPage, Integer pageSize) {
        PageInfo<SkBbsReply> pageInfo = null;
        PageHelper.startPage(currentPage,pageSize);
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectAll();
        pageInfo = new PageInfo<>(skBbsReplies);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(SkBbsReply record) {
        return skBbsReplyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateAd(String id) {
        List<String> list = Tools.getList(id);
        skBbsReplyMapper.updateAd(list);
    }

    @Override
    public PageInfo<SkBbsReply> selectAllStatus(Integer currentPage, Integer pageSize) {
        PageInfo<SkBbsReply>  pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectAllStatus();
        pageInfo = new PageInfo<>(skBbsReplies);
        return pageInfo;
    }

    @Override
    public PageInfo<SkBbsReply> selectAllNoStatus(Integer currentPage, Integer pageSize) {
        PageInfo<SkBbsReply>  pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectAllNoStatus();
        pageInfo = new PageInfo<>(skBbsReplies);
        return pageInfo;
    }


}

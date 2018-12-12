package com.niit.service.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.bbs.dao.SkBbsReplyMapper;
import com.niit.service.bbs.service.SkBbsReplyService;
import com.niit.service.bbs.pojo.SkBbsReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date:2018/12/04 10:53
 * @Description:
 */
@Service
public class SkBbsReplyServiceImpl implements SkBbsReplyService {
    @Autowired
    private SkBbsReplyMapper skBbsReplyMapper;

    @Override
    public int insertSelective(SkBbsReply record) {
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

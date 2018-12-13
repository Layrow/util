package com.niit.service.bbs.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.bbs.pojo.SkBbsReply;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date:2018/12/04 10:47
 * @Description:
 */
public interface SkBbsReplyService {

    int insertSelective(SkBbsReply record);

    int deleteByPrimaryKey(Integer id);
    //查询所有回复帖总数
    String selectCount(Integer topicId);
    //查询帖子的所有回复帖
    PageInfo<SkBbsReply> selectAllReply(Integer topicId, Integer currentPage, Integer pageSize);
    //最新回复日期
    Map<String,Object> selectDate(Integer topicId);

    void deleteAd(String id);

    //查询所有回帖
    PageInfo<SkBbsReply> selectAll(Integer currentPage, Integer pageSize);

    int updateByPrimaryKeySelective(SkBbsReply record);

    // 批量审核
    void  updateAd(String id);


    //查询审核
    PageInfo<SkBbsReply> selectAllStatus(Integer currentPage, Integer pageSize);

    //查询未审核
    PageInfo<SkBbsReply>  selectAllNoStatus(Integer currentPage, Integer pageSize);
    //查询用户的所有回帖
    PageInfo<SkBbsReply>  selectReply(Integer currentPage, Integer pageSize,String replyUserId);


    Map<Integer,List<Object>> replyInfo(Integer currentPage, Integer pageSize, Integer sectionId);
}

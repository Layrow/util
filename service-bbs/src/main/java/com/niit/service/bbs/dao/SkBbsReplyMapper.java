package com.niit.service.bbs.dao;

import com.niit.service.bbs.pojo.SkBbsReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkBbsReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkBbsReply record);

    SkBbsReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkBbsReply record);

    int updateByPrimaryKeyWithBLOBs(SkBbsReply record);

    int updateByPrimaryKey(SkBbsReply record);

    int insertSelective(SkBbsReply record);

    //查询当前帖子所有回复帖（排序）
    List<SkBbsReply> selectAllReply(Integer topicId);

    //查看某个帖子的所有评论数
    String selectCount(Integer topicId);

    /**
     * 根据传递的Ids查询所有的回复贴
     * @param ids
     * @return
     */
    List<SkBbsReply>selectReplyByIds(List<Integer> ids);

    //最新回复时间
    Map<String,Object> selectDate(Integer topicId);

    //批量删除回帖
    void  deleteAd(@Param("list") List<String> list);

    //查询所有回帖
    List<SkBbsReply> selectAll();
    // 批量审核
    void  updateAd(@Param("list") List<String> list);

    //查询审核
    List<SkBbsReply>  selectAllStatus();

    //查询未审核
    List<SkBbsReply>  selectAllNoStatus();
    //查询用户的所有回帖
    List<SkBbsReply>  selectReply(@Param("replyUserid") String replyUserId);

















}
package com.niit.service.bbs.dao;

import com.niit.service.bbs.pojo.SkBbsTopic;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SkBbsTopicMapper {
    /**
     * 删除帖子根据帖子的主键Id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 管理员 批量删除 帖子
     * @param id
     * @return
     */
    int deleteByPrimaryKeyList(List<String> id);

    int insert(SkBbsTopic record);

    /**
     * 发表帖子
     * @param record  帖子对象
     * @return
     */
    int insertSelective(SkBbsTopic record);

    /**
     * 更新帖子浏览量
     * @param id 帖子主键
     * @param newCount 新的浏览量
     * @return
     */
    int updateViewCountByPrimaryKey(@Param(value = "id") Integer id,@Param(value = "newCount") Integer newCount);

    /**
     * 分页显示所有的 帖子
     * @return
     */
    List<SkBbsTopic> listAllTopic();

    /**
     * 分页显示指定栏目下的帖子
     * @param sectionId  指定栏目Id
     * @return
     */
    List<SkBbsTopic>  listAllTopicInSection(Integer sectionId);

    /**
     * 分页 模糊查询
     * @param title  查询关键字
     * @param key  帖子的不同状态标示
     * @return
     */
    List<SkBbsTopic> likeSelectAll(@Param("title") String title,@Param("key") String key);

    /**
     * 分页查询,指定用户的所有发帖
     * @param userId 指定的用户Id
     * @return
     */
    List<SkBbsTopic> listAllTopicByOwner(String userId);
    /**
     * 实现帖子的批量审核,执行到此时,List集合内一定有数据
     * @param ids
     * @return
     */
    boolean doCheck(List<String> ids);

    /**
     * 批量置顶
     * @param ids 需要置顶的帖子ID
     * @return
     */
    boolean doTop(List<String> ids);

    /**
     * 批量加精
     * @param ids
     * @return
     */
    boolean doEssence(List<String> ids);
    boolean doOffcial(List<String> ids);
    /**
     * 实现帖子的批量取消审核,执行到此时,List集合内一定有数据
     * @param ids
     * @return
     */
    boolean doUnCheck(List<String> ids);

    /**
     * 批量取消置顶
     * @param ids 需要置顶的帖子ID
     * @return
     */
    boolean doUnTop(List<String> ids);

    /**
     * 批量取消加精
     * @param ids
     * @return
     */
    boolean doUnEssence(List<String> ids);

    /**
     * 根据Id查询单个帖子
     * @param id
     * @return
     */
    SkBbsTopic selectByPrimaryKey(Integer id);

    /**
     * 修改 单个 帖子信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SkBbsTopic record);

    int updateByPrimaryKeyWithBLOBs(SkBbsTopic record);

    int updateByPrimaryKey(SkBbsTopic record);
}
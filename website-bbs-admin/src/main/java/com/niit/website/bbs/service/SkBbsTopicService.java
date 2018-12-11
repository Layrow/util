package com.niit.website.bbs.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.bbs.pojo.SkBbsTopic;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/6
 * @since 1.0.0
 */
public interface SkBbsTopicService {

    /**
     * 单个修改帖子
     * @param record
     * @return
     */
    boolean editOne(SkBbsTopic record);

    /**
     * 客户端发表帖子
     * @param record  帖子对象
     * @return
     */
    boolean insertSelective(SkBbsTopic record);

    /**
     * 删除帖子根据帖子的主键Id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 删除帖子根据帖子的主键Ids
     * @param ids
     * @return
     */
    boolean batchDel(String ids);
    /**
     * 更新帖子浏览量
     * @param id 帖子主键
     * @param newCount 新的浏览量
     * @return
     */
    int updateViewCountByPrimaryKey(Integer id, Integer newCount);


    /**
     * 分页显示所有的 帖子
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return
     */
    PageInfo<SkBbsTopic> listAllTopic(Integer currentPage, Integer pageSize);

    /**
     * 分页显示指定栏目下的帖子
     * @param sectionId  指定栏目Id
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return
     */
    PageInfo<SkBbsTopic>  listAllTopicInSection(Integer currentPage, Integer pageSize, Integer sectionId);

    /**
     * 分页 模糊查询
     * @param title  查询关键字
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return
     */
    PageInfo<SkBbsTopic> likeSelectAll(Integer currentPage, Integer pageSize, String title,String key);

    /**
     * 分页查询,指定用户的所有发帖
     * @param userId 指定的用户Id
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return
     */
    PageInfo<SkBbsTopic> listAllTopicByOwner(Integer currentPage, Integer pageSize, String userId);

    /**
     * 批量审核 帖子
     * @param id 审核的帖子的集合
     * @return
     */
    boolean check(String id);

    /**
     * 批量置顶
     * @param id
     * @return
     */
    boolean top(String id);

    /**
     * 批量加精
     * @param id
     * @return
     */
    boolean essence(String id);
    /**
     * 批量官宣
     * @param id
     * @return
     */
    boolean offcial(String id);

    /**
     * 批量审核 帖子
     * @param id 审核的帖子的集合
     * @return
     */
    boolean unCheck(String id);

    /**
     * 批量置顶
     * @param id
     * @return
     */
    boolean unTop(String id);

    /**
     * 批量加精
     * @param id
     * @return
     */
    boolean unEssence(String id);
}

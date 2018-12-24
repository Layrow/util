package com.niit.website.smartkids.service.projectservice;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:07
 **/
public interface SkProjectService {

    // insert
    void insert(SkProject record);

    // delete
    void deleteByPrimaryKey(Integer id, Integer userId, String userName);

    // select
    SkProject selectByPrimaryKey(Integer id);

    // update
    void updateByPrimaryKey(SkProject record);

    // 批量删除作品
    void deleteMoreProject(String id);

    // 查询所有作品
    PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize);

    // 批量审核作品
    void updateMoreProject(String id);

    // 根据title模糊查询作品
    PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize);

    // 按置顶，推荐进行查询
    PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize);

    // 查询个人作品
    PageInfo<SkProject> selectProjectByUserId(Integer userId,Integer currentPage,Integer pageSize);

    // TODO project
    // 根据栏目ID查询相应的作品，状态码默认为1，orderBy排序 ，分页title模糊查询
    // 发布作品 积分变更
    // 删除作品 积分变更
    // 点赞作品收藏作品 积分变更
    // 重复点赞判断，积分变更通知
}

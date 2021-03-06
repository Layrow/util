package com.niit.website.cms.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkProject;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:07
 **/
public interface SkProjectService {

    // insert
    void insert(SkProject record);

    // delete
    void deleteByPrimaryKey(Integer id);

    // select
    SkProject selectByPrimaryKey(Integer id);

    // update
    void updateByPrimaryKey(SkProject record);

    // 批量删除作品
    void deleteMoreProject(String id);

    // 查询所有作品
    PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize);

    // 批量操作作品（审核，推荐，置顶）
    void updateMoreProject(String sign, String id);

    // 根据title模糊查询作品
    PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize);

    // 按置顶，推荐进行查询
    PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize);

    // 查询个人作品
    PageInfo<SkProject> selectProjectByUserId(Integer userId, Integer currentPage, Integer pageSize);
}

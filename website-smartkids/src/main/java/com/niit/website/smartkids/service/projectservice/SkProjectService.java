package com.niit.website.smartkids.service.projectservice;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.project.SkProject;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:07
 **/
public interface SkProjectService {

    // insert
    void insertProject(SkProject record);

    // delete
    void deleteByPrimaryKey(Integer id);

    // select
    String selectByPrimaryKey(Integer id);

    // select
    SkProject selectProjectByPrimaryKey(Integer id);

    List selectProjectOperation(Integer user_id, Integer project_id);

    // update
    void updateByPrimaryKey(SkProject record, Integer operationUserId, String operationUserName, String operate);

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
    String selectProjectByUserId(Integer userId);

    void addProjectViewCount(int id);


}

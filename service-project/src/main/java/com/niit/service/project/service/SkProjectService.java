package com.niit.service.project.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.project.pojo.SkProject;

import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:07
 **/
public interface SkProjectService {

    // insert
    String insert(SkProject record);

    // delete
    int deleteByPrimaryKey(Integer id);

    // select
    String selectByPrimaryKey(Integer id);

    SkProject selectProjectInfo(Integer id);

    List selectProjectOperation(Integer user_id, Integer project_id);

    // update
    int updateByPrimaryKey(SkProject record);

    // 批量删除作品
    int deleteMoreProject(String id);

    // 查询所有作品
    PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize);

    // 批量操作作品（置顶，推荐，审核）
    Integer updateMoreProject(String sign, String id);

    // 根据title模糊查询作品
    PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize);

    // 按置顶，推荐进行查询
    PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize);

    // 查询个人作品
    String selectProjectByUserId(Integer userId);

    // 增加作品浏览量
    int addProjectViewCount(int id);
}


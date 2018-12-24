package com.niit.service.project.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.project.pojo.SkProject;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:07
 **/
public interface SkProjectService {

    // insert
    int insert(SkProject record);

    // delete
    int deleteByPrimaryKey(Integer id);

    // select
    SkProject selectByPrimaryKey(Integer id);

    // update
    int updateByPrimaryKey(SkProject record);

    // 批量删除作品
    int deleteMoreProject(String id);

    // 查询所有作品
    PageInfo<SkProject> selectAllProject(Integer status,Integer currentPage,Integer pageSize);

    // 批量审核作品
    Integer updateMoreProject(String id);

    // 根据title模糊查询作品
    PageInfo<SkProject> likeSelectProjectByTitle(String status,String title,Integer currentPage,Integer pageSize);
}
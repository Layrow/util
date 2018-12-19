package com.niit.service.project.service;

import com.niit.service.project.pojo.SkProject;

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
}

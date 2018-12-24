package com.niit.service.project.service;

import com.niit.service.project.pojo.SkProjectComments;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:06
 **/
public interface SkProjectCommentsService {

    // delete
    int deleteByPrimaryKey(Integer id);

    // insert
    int insert(SkProjectComments record);

    // select
    SkProjectComments selectByPrimaryKey(Integer id);

    // update
    int updateByPrimaryKey(SkProjectComments record);

    // 批量审核
    Integer updateMoreProjectComment(String id);

    // 批量删除
    Integer deleteMoreProjectComment(String id);

}

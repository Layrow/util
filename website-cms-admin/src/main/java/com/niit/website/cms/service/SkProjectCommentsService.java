package com.niit.website.cms.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkProjectComments;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:06
 **/
public interface SkProjectCommentsService {

    // delete
    void deleteByPrimaryKey(Integer id);

    // insert
    void insert(SkProjectComments record);

    // select
    SkProjectComments selectByPrimaryKey(Integer id);

    // update
    void updateByPrimaryKey(SkProjectComments record);

    // 批量审核
    void updateMoreProjectComment(String id);

    // 批量删除
    void deleteMoreProjectComment(String id);

    // 查询所有作品留言
    PageInfo<SkProjectComments> selectAllProjectComment(Integer projectId,Integer currentPage, Integer pageSize);

}

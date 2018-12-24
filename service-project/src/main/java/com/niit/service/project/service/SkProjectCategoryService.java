package com.niit.service.project.service;

import com.niit.service.project.pojo.SkProjectCategory;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:06
 **/
public interface SkProjectCategoryService {

    // delete
    int deleteByPrimaryKey(Integer id);

    // insert
    int insert(SkProjectCategory record);

    // select
    SkProjectCategory selectByPrimaryKey(Integer id);

    // update
    int updateByPrimaryKey(SkProjectCategory record);

    // 查询所有的作品栏目类别
    List<SkProjectCategory> selectAllProjectCategory();

    // 批量删除
    Integer deleteMoreProjectCategory(String id);
}

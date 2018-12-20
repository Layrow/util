package com.niit.service.project.service;

import com.niit.service.project.pojo.SkProjectCategory;

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
}

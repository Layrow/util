package com.niit.website.smartkids.service.projectservice;


import com.niit.website.smartkids.pojo.project.SkProjectCategory;

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

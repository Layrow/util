package com.niit.website.smartkids.service.projectservice;


import com.niit.website.smartkids.pojo.project.SkProjectCategory;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:06
 **/
public interface SkProjectCategoryService {

    // delete
    void deleteByPrimaryKey(Integer id);

    // insert
    void insert(SkProjectCategory record);

    // select
    SkProjectCategory selectByPrimaryKey(Integer id);

    // update
    void updateByPrimaryKey(SkProjectCategory record);

    // 查询所有的作品栏目类别
    List<SkProjectCategory> selectAllProjectCategory();
}

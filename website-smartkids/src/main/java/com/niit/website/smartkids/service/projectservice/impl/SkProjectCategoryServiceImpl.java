package com.niit.website.smartkids.service.projectservice.impl;

import com.niit.website.smartkids.pojo.project.SkProjectCategory;
import com.niit.website.smartkids.service.projectservice.SkProjectCategoryService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkProjectCategoryServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCategoryServiceImpl implements SkProjectCategoryService {


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(SkProjectCategory record) {
        return 0;
    }

    @Override
    public SkProjectCategory selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SkProjectCategory record) {
        return 0;
    }
}

package com.niit.service.project.service.impl;

import com.niit.service.project.dao.SkProjectCategoryMapper;
import com.niit.service.project.pojo.SkProjectCategory;
import com.niit.service.project.service.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkProjectCategoryServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCategoryServiceImpl implements SkProjectCategoryService {

    @Autowired
    private SkProjectCategoryMapper skProjectCategoryMapper;

    // delete
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skProjectCategoryMapper.deleteByPrimaryKey(id);
    }

    // insert
    @Override
    public int insert(SkProjectCategory record) {
        return skProjectCategoryMapper.insert(record);
    }

    // select
    @Override
    public SkProjectCategory selectByPrimaryKey(Integer id) {
        return skProjectCategoryMapper.selectByPrimaryKey(id);
    }

    // update
    @Override
    public int updateByPrimaryKey(SkProjectCategory record) {
        return skProjectCategoryMapper.updateByPrimaryKey(record);
    }
}

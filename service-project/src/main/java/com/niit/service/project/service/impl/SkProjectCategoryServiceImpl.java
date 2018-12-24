package com.niit.service.project.service.impl;

import com.niit.common.utils.Tools;
import com.niit.service.project.dao.SkProjectCategoryMapper;
import com.niit.service.project.pojo.SkProjectCategory;
import com.niit.service.project.service.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 查询所有的作品栏目类别
    @Override
    public List<SkProjectCategory> selectAllProjectCategory() {
        return skProjectCategoryMapper.selectAllProjectCategory();
    }

    // 批量删除
    @Override
    public Integer deleteMoreProjectCategory(String id) {
        List<String> list = Tools.getList(id);
        return skProjectCategoryMapper.deleteMoreProjectCategory(list);
    }
}

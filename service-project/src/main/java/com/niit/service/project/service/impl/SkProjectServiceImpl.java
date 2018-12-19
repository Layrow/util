package com.niit.service.project.service.impl;

import com.niit.common.utils.Tools;
import com.niit.service.project.dao.SkProjectMapper;
import com.niit.service.project.pojo.SkProject;
import com.niit.service.project.service.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SkProjectServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectServiceImpl implements SkProjectService {

    @Autowired
    private SkProjectMapper skProjectMapper;

    // insert
    @Override
    public int insert(SkProject record) {
        return skProjectMapper.insert(record);
    }

    // delete
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skProjectMapper.deleteByPrimaryKey(id);
    }

    // select
    @Override
    public SkProject selectByPrimaryKey(Integer id) {
        return skProjectMapper.selectByPrimaryKey(id);
    }

    // update
    @Override
    public int updateByPrimaryKey(SkProject record) {
        return skProjectMapper.updateByPrimaryKey(record);
    }

    // delete more
    @Override
    public int deleteMoreProject(String id) {
        List<String> list = Tools.getList(id);
        return skProjectMapper.deleteMoreProject(list);
    }
}

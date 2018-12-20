package com.niit.service.project.service.impl;

import com.niit.service.project.dao.SkProjectCommentsMapper;
import com.niit.service.project.pojo.SkProjectComments;
import com.niit.service.project.service.SkProjectCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkProjectCommentsServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCommentsServiceImpl implements SkProjectCommentsService {

    @Autowired
    private SkProjectCommentsMapper skProjectCommentsMapper;

    // delete
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skProjectCommentsMapper.deleteByPrimaryKey(id);
    }

    // insert
    @Override
    public int insert(SkProjectComments record) {
        return skProjectCommentsMapper.insert(record);
    }

    // select
    @Override
    public SkProjectComments selectByPrimaryKey(Integer id) {
        return skProjectCommentsMapper.selectByPrimaryKey(id);
    }

    // update
    @Override
    public int updateByPrimaryKey(SkProjectComments record) {
        return skProjectCommentsMapper.updateByPrimaryKey(record);
    }
}

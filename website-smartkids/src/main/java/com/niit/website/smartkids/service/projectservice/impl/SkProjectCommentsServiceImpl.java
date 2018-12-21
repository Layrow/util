package com.niit.website.smartkids.service.projectservice.impl;

import com.niit.website.smartkids.pojo.project.SkProjectComments;
import com.niit.website.smartkids.service.projectservice.SkProjectCommentsService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkProjectCommentsServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:08
 **/
@Service
public class SkProjectCommentsServiceImpl implements SkProjectCommentsService {


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(SkProjectComments record) {
        return 0;
    }

    @Override
    public SkProjectComments selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SkProjectComments record) {
        return 0;
    }

    @Override
    public Integer updateMoreProjectComment(String id) {
        return null;
    }

    @Override
    public Integer deleteMoreProjectComment(String id) {
        return null;
    }
}

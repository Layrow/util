package com.niit.service.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.project.dao.SkProjectMapper;
import com.niit.service.project.pojo.SkProject;
import com.niit.service.project.service.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    // select more
    @Override
    public PageInfo<SkProject> selectAllProject(Integer status, Integer currentPage, Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkProject> skProjectList = skProjectMapper.selectAllProject(status);
        pageInfo = new PageInfo<>(skProjectList);
        return pageInfo;
    }


    // 批量审核作品
    @Override
    public Integer updateMoreProject(String id) {
        List<String> list = Tools.getList(id);
        return skProjectMapper.updateMoreProject(list);
    }

    // 根据title模糊查询作品
    @Override
    public PageInfo<SkProject> likeSelectProjectByTitle(String status,String title,Integer currentPage,Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkProject> skProjectList = skProjectMapper.likeSelectProjectByTitle(status,title);
        pageInfo = new PageInfo<>(skProjectList);
        return pageInfo;
    }
}

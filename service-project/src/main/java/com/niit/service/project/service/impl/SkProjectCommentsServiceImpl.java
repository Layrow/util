package com.niit.service.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.project.dao.SkProjectCommentsMapper;
import com.niit.service.project.pojo.SkProjectComments;
import com.niit.service.project.service.SkProjectCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 批量审核
    @Override
    public Integer updateMoreProjectComment(String id) {
        List<String> list = Tools.getList(id);
        return skProjectCommentsMapper.updateMoreProjectComment(list);
    }

    // 批量删除作品留言
    @Override
    public Integer deleteMoreProjectComment(String id) {
        List<String> list = Tools.getList(id);
        return skProjectCommentsMapper.deleteMoreProjectComment(list);
    }

    // 查询所有留言
    @Override
    public PageInfo<SkProjectComments> selectAllProjectComment(Integer projectId, Integer currentPage, Integer pageSize) {
        PageInfo<SkProjectComments> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkProjectComments> list = skProjectCommentsMapper.selectAllProjectComment(projectId);
        pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

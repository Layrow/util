package com.niit.service.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.common.utils.Tools;
import com.niit.service.project.dao.SkProjectMapper;
import com.niit.service.project.pojo.SkProject;
import com.niit.service.project.service.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public String insert(SkProject record) {
        skProjectMapper.insert(record);
        Integer id = record.getId();
        return String.valueOf(id);
    }

    // delete
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skProjectMapper.deleteByPrimaryKey(id);
    }

    // select
    @Override
    public String selectByPrimaryKey(Integer id) {
        LinkedHashMap<String, Object> projectMap = skProjectMapper.selectByPrimaryKey(id);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Date updatetime = (Date) projectMap.get("updatetime");
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatTime.format(updatetime);
        projectMap.put("updatetime", s);
        return gson.toJson(projectMap);
    }

    @Override
    public SkProject selectProjectInfo(Integer id) {
        return skProjectMapper.selectProjectInfo(id);
    }

    @Override
    public List selectProjectOperation(Integer user_id, Integer project_id) {
        return skProjectMapper.selectProjectOperation(user_id, project_id);
    }

    // update
    @Override
    public int updateByPrimaryKey(SkProject record) {
        return skProjectMapper.updateByPrimaryKeySelective(record);
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


    // 批量操作作品（置顶，推荐，审核）
    @Override
    public Integer updateMoreProject(String sign, String id) {
        List<String> list = Tools.getList(id);
        return skProjectMapper.updateMoreProject(sign, list);
    }

    // 根据title模糊查询作品
    @Override
    public PageInfo<SkProject> likeSelectProjectByTitle(String status, String title, Integer currentPage, Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkProject> skProjectList = skProjectMapper.likeSelectProjectByTitle(status, title);
        pageInfo = new PageInfo<>(skProjectList);
        return pageInfo;
    }

    // 按照置顶/推荐查询
    @Override
    public PageInfo<SkProject> likeSelectProjectAll(String title, Integer status, Integer categoryId, String orderBy, Integer currentPage, Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkProject> skProjectList = skProjectMapper.likeSelectProjectAll(title, status, categoryId, orderBy);
        pageInfo = new PageInfo<>(skProjectList);
        return pageInfo;
    }

    // 查询用户作品
    @Override
    public String selectProjectByUserId(Integer userId) {
        HashMap<String, Object> allPresonProjectInfo = new HashMap<>();
        List<LinkedHashMap<String, Object>> map = skProjectMapper.selectProjectByUserId(userId);
        List<LinkedHashMap<String, Object>> likeProjectList = map.stream().filter(x -> x.containsKey("operation") && (Integer) x.get("operation") == 2).collect(Collectors.toList());
        List<LinkedHashMap<String, Object>> collectProjectList = map.stream().filter(x -> x.containsKey("operation") && (Integer) x.get("operation") == 3).collect(Collectors.toList());
        List<LinkedHashMap<String, Object>> createProjectList = map.stream().filter(x -> x.containsKey("operation") == false).collect(Collectors.toList());
        allPresonProjectInfo.put("likeProjectList", likeProjectList);
        allPresonProjectInfo.put("collectProjectList", collectProjectList);
        allPresonProjectInfo.put("createProjectList", createProjectList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(allPresonProjectInfo);
    }


    // add project view_count
    @Override
    public int addProjectViewCount(int id) {
        return skProjectMapper.addProjectViewCount(id);
    }
}

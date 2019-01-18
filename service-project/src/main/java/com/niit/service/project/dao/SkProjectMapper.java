package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SkProjectMapper {

    // delete
    int deleteByPrimaryKey(Integer id);

    // insert
    int insert(SkProject record);

    int insertSelective(SkProject record);

    // select
    LinkedHashMap<String, Object> selectByPrimaryKey(Integer id);

    SkProject selectProjectInfo(Integer id);

    List selectProjectOperation(@Param("user_id") Integer user_id, @Param("project_id") Integer project_id);

    int updateByPrimaryKeySelective(SkProject record);

    // update
    int updateByPrimaryKey(SkProject record);

    // 批量删除作品
    int deleteMoreProject(List<String> list);

    // 查询所有作品
    List<SkProject> selectAllProject(@Param("status") Integer status);

    // 批量操作作品（置顶，推荐，审核）
    Integer updateMoreProject(@Param("sign") String sign, @Param("list") List<String> list);

    // 根据title模糊查询作品
    List<SkProject> likeSelectProjectByTitle(String status, String title);

    // 按置顶，推荐进行查询
    List<SkProject> likeSelectProjectAll(@Param("title") String title, @Param("status") Integer status, @Param("categoryId") Integer categoryId, @Param("orderBy") String orderBy);

//    // 查询某个用户的作品(创作、收藏、点赞)
//    List<SkProject> selectProjectByUserId(Integer userId);

    // 查询某个用户的作品(创作、收藏、点赞)
    List<LinkedHashMap<String, Object>> selectProjectByUserId(Integer userId);

    // 增加作品浏览量
    int addProjectViewCount(int id);
}
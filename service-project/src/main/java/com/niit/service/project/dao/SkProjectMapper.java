package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkProjectMapper {

    // delete
    int deleteByPrimaryKey(Integer id);

    // insert
    int insert(SkProject record);

    int insertSelective(SkProject record);

    // select
    SkProject selectByPrimaryKey(Integer id);

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
    List<SkProject> likeSelectProjectByTitle(String status,String title);

    // 按置顶，推荐进行查询
    List<SkProject> likeSelectProjectAll(@Param("title")String title,@Param("status") Integer status,@Param("categoryId") Integer categoryId,@Param("orderBy") String orderBy);

    // 查询个人作品
    List<SkProject> selectProjectByUserId(Integer userId);
}
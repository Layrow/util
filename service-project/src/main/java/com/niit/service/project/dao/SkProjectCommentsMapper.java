package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProjectComments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkProjectCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkProjectComments record);

    int insertSelective(SkProjectComments record);

    SkProjectComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkProjectComments record);

    int updateByPrimaryKey(SkProjectComments record);

    // 批量审核
    Integer updateMoreProjectComment(List<String > list);

    // 批量删除
    Integer deleteMoreProjectComment(List<String> list);

    // 查询所有留言
    List<SkProjectComments> selectAllProjectComment(@Param("projectId") Integer projectId);
}
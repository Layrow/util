package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProject;
import org.apache.ibatis.annotations.Mapper;

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
}
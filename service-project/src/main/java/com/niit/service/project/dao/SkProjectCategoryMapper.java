package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProjectCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkProjectCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkProjectCategory record);

    int insertSelective(SkProjectCategory record);

    SkProjectCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkProjectCategory record);

    int updateByPrimaryKey(SkProjectCategory record);
}
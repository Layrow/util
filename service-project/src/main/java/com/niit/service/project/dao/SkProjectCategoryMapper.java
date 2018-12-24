package com.niit.service.project.dao;


import com.niit.service.project.pojo.SkProjectCategory;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SkProjectCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkProjectCategory record);

    int insertSelective(SkProjectCategory record);

    SkProjectCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkProjectCategory record);

    int updateByPrimaryKey(SkProjectCategory record);

    // 查询所有的作品栏目类别
    List<SkProjectCategory> selectAllProjectCategory();

    // 批量删除
    Integer deleteMoreProjectCategory(List<String> list);

}
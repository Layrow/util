package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsBatchCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkLmsBatchCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchCn record);

    int insertSelective(SkLmsBatchCn record);

    SkLmsBatchCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchCn record);

    int updateByPrimaryKey(SkLmsBatchCn record);

    // 查询特定教师下的班级
    List<SkLmsBatchCn> selectByFacultyId(Integer facultyId);
}
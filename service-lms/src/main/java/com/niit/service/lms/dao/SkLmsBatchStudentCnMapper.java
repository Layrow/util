package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsBatchStudentCn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkLmsBatchStudentCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchStudentCn record);

    int insertSelective(SkLmsBatchStudentCn record);

    SkLmsBatchStudentCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchStudentCn record);

    int updateByPrimaryKey(SkLmsBatchStudentCn record);

    /**
     * 学生班级关联表的字段删除
     * @param studentId 需要删除的学生ID
     * @return
     */
    int deleteByStudentID(Integer studentId);
}
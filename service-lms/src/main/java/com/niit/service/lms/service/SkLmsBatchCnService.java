package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsBatchCn;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 13:24
 **/
public interface SkLmsBatchCnService {

    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchCn record);

    int insertSelective(SkLmsBatchCn record);

    SkLmsBatchCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchCn record);

    int updateByPrimaryKey(SkLmsBatchCn record);
    // 查询特定教师下的班级
    List<SkLmsBatchCn> selectByFacultyId(Integer facultyId);
}

package com.niit.website.lms.service;


import com.niit.website.lms.pojo.SkLmsBatchCn;

import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 13:24
 **/
public interface SkLmsBatchCnService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SkLmsBatchCn record);

    // SkLmsBatchCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchCn record);

    // 查询特定教师下的班级
    List<SkLmsBatchCn> selectByFacultyId(Integer facultyId);
}

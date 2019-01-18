package com.niit.website.lms.service;

import com.niit.website.lms.pojo.SkLmsBatchCoursewareCn;

import java.util.List;


/**
 * @Auther: huangwei
 * @Date: 2018/11/8 14:54
 * @Description:
 */
public interface SkLmsBatchCoursewareCnService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SkLmsBatchCoursewareCn record);

    //共享（已共享的班级查询）
    String select(Integer coursewareId, Integer facultyId);

    //批量删除
    void deleteAd(String batch_id, Integer coursewareId);

    String insertBatch(List<SkLmsBatchCoursewareCn> sk);

}

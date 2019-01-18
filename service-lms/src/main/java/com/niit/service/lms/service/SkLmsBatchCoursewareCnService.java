package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsBatchCoursewareCn;

import java.util.List;
import java.util.Map;


/**
 * @Auther: huangwei
 * @Date: 2018/11/8 14:25
 * @Description:
 */
public interface SkLmsBatchCoursewareCnService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SkLmsBatchCoursewareCn record);

    //批量删除
    void deleteAd(String batch_id, Integer coursewareId);

    //查询全部班级（包括共享的班级）
    List<Map<String, Object>> select(Integer coursewareId, Integer facultyId);

    //  List<Map<String, Object>> shareBatch(Integer coursewareId,Integer facultyId);
    int insertBatch(List<SkLmsBatchCoursewareCn> sk);
}



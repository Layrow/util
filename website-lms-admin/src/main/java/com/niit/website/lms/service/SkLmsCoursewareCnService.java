package com.niit.website.lms.service;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 17:23
 * @Description:
 */
public interface SkLmsCoursewareCnService {
    int deleteByPrimaryKey(Integer id);

    //模糊查询
    String selectAll(Integer facultyId, int currentPage, int pageSize, String courseware_title);

    Integer insertSelective(String url);
    //查询所有

    String selectAllWare(Integer facultyId, int currentPage, int pageSize);

    //查询当前班级下的所有课件
    String selectAllBatchWare(Integer batchId, int currentPage, int pageSize);


}

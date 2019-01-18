package com.niit.website.lms.service;

import com.niit.website.lms.pojo.SkLmsHomeworkCn;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 15:33
 **/
public interface SkLmsHomeworkCnService {
    // 布置作业 上传课件 共享班级
    void insertHomework(String json) throws Exception;

    // 修改作业
    void updateHomework(String json) throws Exception;

    // 删除作业
    void deleteHomework(Integer id);

    // 特定老师布置的作业
    List<SkLmsHomeworkCn> selectHomeworkByFacultyId(Integer facultyId);

    // 查询特定班级
    String selectByPrimaryKey(Integer id);

    // 查询谁提交了那份作业
    String selectStudentByHomeworkId(Integer batchId, Integer homeworkId, Integer currentPage, Integer pageSize);

    // 查询特定班级下的所有作业
    String selectHomeworkByBatchId(Integer batchId);

    // 查询特定班级下的特定作业下的得分情况
    String selectScore(Integer batchId, Integer homeworkId);

}

package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsHomeworkCn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 15:33
 **/
public interface SkLmsHomeworkCnService {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsHomeworkCn record);

    int insertSelective(SkLmsHomeworkCn record);

    String selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsHomeworkCn record);

    int updateByPrimaryKeyWithBLOBs(SkLmsHomeworkCn record);

    int updateByPrimaryKey(SkLmsHomeworkCn record);

    // 查询特定老师布置的作业
    List<SkLmsHomeworkCn> selectByFacultyId(Integer facultyId);

    // 布置作业 上传课件 共享班级
    void insertHomework(String json) throws Exception;

    // 修改作业
    void updateHomework(String json) throws Exception;

    // 删除作业
    void deleteHomework(Integer id);

    // 特定老师布置的作业
    List<SkLmsHomeworkCn> selectHomeworkByFacultyId(Integer facultyId);

    // 查询谁提交了那份作业
    // List<SkLmsStudentsCn> selectStudentByHomeworkId(Integer homeworkId);
    String selectStudentByHomeworkId(Integer batchId, Integer homeworkId, Integer currentPage, Integer pageSize);

    // 查询谁提交了和没提交那份作业
    List<LinkedHashMap<String, Object>> selectNotdoStudentByHomeworkId(Integer batchId, Integer homeworkId);

    // 查询特定班级下的所有作业
    String selectHomeworkByBatchId(Integer batchId);

    // 查询特定班级下的特定作业下的得分情况
    String selectScore(Integer batchId, Integer homeworkId);

}

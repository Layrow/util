package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsHomeworkAnswerCn;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 10:38
 **/
public interface SkLmsHomeworkAnswerCnService {

    // 查询作业回答的详细信息
    String selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId);

    int updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record);

    // 学生作业信息的分析
    String selectStudentHomeworkInfo(Integer batchId, Integer studentId);

    // 班级作业情况分析
    String selectBacthInfo(Integer batchId);
}

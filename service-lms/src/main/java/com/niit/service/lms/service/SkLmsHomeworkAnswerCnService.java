package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsHomeworkAnswerCn;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 10:38
 **/
public interface SkLmsHomeworkAnswerCnService {

    // 查询作业回答的详细信息
    String selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId);

    int updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record);
}

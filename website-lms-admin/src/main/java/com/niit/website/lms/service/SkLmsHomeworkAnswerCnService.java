package com.niit.website.lms.service;

import com.niit.website.lms.pojo.SkLmsHomeworkAnswerCn;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 11:16
 **/
public interface SkLmsHomeworkAnswerCnService {

    // 查询作业回答的详细信息
    String selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId);

    void updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record);
}

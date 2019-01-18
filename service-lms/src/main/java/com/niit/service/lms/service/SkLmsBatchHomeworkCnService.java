package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsBatchHomeworkCn;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/14 12:49
 **/
public interface SkLmsBatchHomeworkCnService {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchHomeworkCn record);

    int insertSelective(SkLmsBatchHomeworkCn record);

    SkLmsBatchHomeworkCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchHomeworkCn record);

    int updateByPrimaryKey(SkLmsBatchHomeworkCn record);

    // 根据homeworkID删除附件与作业的关系
    int deleteByHomeworkId(Integer homeworkId);
}

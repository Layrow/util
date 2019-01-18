package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsBatchHomeworkCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkLmsBatchHomeworkCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchHomeworkCn record);

    int insertSelective(SkLmsBatchHomeworkCn record);

    SkLmsBatchHomeworkCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsBatchHomeworkCn record);

    // yu
    List<Integer> selectByBatchId(Integer batch_id);

    int updateByPrimaryKey(SkLmsBatchHomeworkCn record);

    // 根据homeworkID删除附件与作业的关系
    int deleteByHomeworkId(Integer homeworkId);
}
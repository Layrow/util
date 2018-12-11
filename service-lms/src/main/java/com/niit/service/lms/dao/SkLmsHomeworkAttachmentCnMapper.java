package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsHomeworkAttachmentCn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkLmsHomeworkAttachmentCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsHomeworkAttachmentCn record);

    int insertSelective(SkLmsHomeworkAttachmentCn record);

    SkLmsHomeworkAttachmentCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsHomeworkAttachmentCn record);

    int updateByPrimaryKey(SkLmsHomeworkAttachmentCn record);
    // 根据homework_id查询课件
    SkLmsHomeworkAttachmentCn selectByHomeworkId(Integer homeworkId);
    // 根据homework_id删除附件
    int deleteByHomeworkId(Integer homeworkId);
}
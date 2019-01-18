package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsHomeworkAnswerAttachmentCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkLmsHomeworkAnswerAttachmentCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsHomeworkAnswerAttachmentCn record);

    int insertSelective(SkLmsHomeworkAnswerAttachmentCn record);

    SkLmsHomeworkAnswerAttachmentCn selectByPrimaryKey(Integer id);

    //根据homeworkAnswerId查找已提交的学生作业的附件ID列表(0-3个)
    List<SkLmsHomeworkAnswerAttachmentCn> selectByHomeworkAnswerId(Integer homeworkAnswerId);

    int updateByPrimaryKeySelective(SkLmsHomeworkAnswerAttachmentCn record);

    int updateByPrimaryKey(SkLmsHomeworkAnswerAttachmentCn record);

    //删除
    int deleteByHomeworkAnswerIdList(List<Integer> attachmentList);
}
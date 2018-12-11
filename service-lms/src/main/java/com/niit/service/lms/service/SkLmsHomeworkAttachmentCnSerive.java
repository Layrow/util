package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsHomeworkAttachmentCn;

/**
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 16:48
 **/
public interface SkLmsHomeworkAttachmentCnSerive {
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

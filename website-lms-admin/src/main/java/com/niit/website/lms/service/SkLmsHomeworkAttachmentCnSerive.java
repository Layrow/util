package com.niit.website.lms.service;

import com.niit.website.lms.pojo.SkLmsHomeworkAttachmentCn;

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
}

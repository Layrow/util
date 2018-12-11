package com.niit.service.lms.service.impl;

import com.niit.service.lms.dao.SkLmsHomeworkAttachmentCnMapper;
import com.niit.service.lms.pojo.SkLmsHomeworkAttachmentCn;
import com.niit.service.lms.service.SkLmsHomeworkAttachmentCnSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkLmsHomeworkAttachmentCnSeriveImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 16:49
 **/
@Service
public class SkLmsHomeworkAttachmentCnSeriveImpl implements SkLmsHomeworkAttachmentCnSerive {
    @Autowired
    private SkLmsHomeworkAttachmentCnMapper skLmsHomeworkAttachmentCnMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skLmsHomeworkAttachmentCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkLmsHomeworkAttachmentCn record) {
        return skLmsHomeworkAttachmentCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkLmsHomeworkAttachmentCn record) {
        return skLmsHomeworkAttachmentCnMapper.insertSelective(record);
    }

    @Override
    public SkLmsHomeworkAttachmentCn selectByPrimaryKey(Integer id) {
        return skLmsHomeworkAttachmentCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkLmsHomeworkAttachmentCn record) {
        return skLmsHomeworkAttachmentCnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SkLmsHomeworkAttachmentCn record) {
        return skLmsHomeworkAttachmentCnMapper.updateByPrimaryKey(record);
    }

    @Override
    public SkLmsHomeworkAttachmentCn selectByHomeworkId(Integer homeworkId) {
        return skLmsHomeworkAttachmentCnMapper.selectByHomeworkId(homeworkId);
    }

    @Override
    public int deleteByHomeworkId(Integer homeworkId) {
        return skLmsHomeworkAttachmentCnMapper.deleteByHomeworkId(homeworkId);
    }
}

package com.niit.service.lms.service.impl;

import com.niit.service.lms.dao.SkLmsBatchHomeworkCnMapper;
import com.niit.service.lms.pojo.SkLmsBatchHomeworkCn;
import com.niit.service.lms.service.SkLmsBatchHomeworkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SkLmsBatchHomeworkCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/14 12:49
 **/
@Service
public class SkLmsBatchHomeworkCnServiceImpl implements SkLmsBatchHomeworkCnService {
    @Autowired
    private SkLmsBatchHomeworkCnMapper skLmsBatchHomeworkCnMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skLmsBatchHomeworkCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkLmsBatchHomeworkCn record) {
        return skLmsBatchHomeworkCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkLmsBatchHomeworkCn record) {
        return skLmsBatchHomeworkCnMapper.insertSelective(record);
    }

    @Override
    public SkLmsBatchHomeworkCn selectByPrimaryKey(Integer id) {
        return skLmsBatchHomeworkCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkLmsBatchHomeworkCn record) {
        return skLmsBatchHomeworkCnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SkLmsBatchHomeworkCn record) {
        return skLmsBatchHomeworkCnMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByHomeworkId(Integer homeworkId) {
        return skLmsBatchHomeworkCnMapper.deleteByHomeworkId(homeworkId);
    }
}

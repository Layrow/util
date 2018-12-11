package com.niit.service.lms.service.impl;

import com.niit.service.lms.dao.SkLmsBatchCnMapper;
import com.niit.service.lms.pojo.SkLmsBatchCn;
import com.niit.service.lms.service.SkLmsBatchCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SkLmsBatchServiceImpl
 * @Description batch service
 * @Author liyuhao
 * @Date 2018/11/8 13:26
 **/
@Service
public class SkLmsBatchServiceImpl implements SkLmsBatchCnService {

    @Autowired
    private SkLmsBatchCnMapper skLmsBatchCnMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skLmsBatchCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkLmsBatchCn record) {
        return skLmsBatchCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkLmsBatchCn record) {
        return skLmsBatchCnMapper.insertSelective(record);
    }

    @Override
    public SkLmsBatchCn selectByPrimaryKey(Integer id) {
        return skLmsBatchCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkLmsBatchCn record) {
        return skLmsBatchCnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SkLmsBatchCn record) {
        return skLmsBatchCnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SkLmsBatchCn> selectByFacultyId(Integer facultyId) {
        return skLmsBatchCnMapper.selectByFacultyId(facultyId);
    }
}

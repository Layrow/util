package com.niit.service.cms.service.impl;

import com.niit.service.cms.dao.SkChannelCnMapper;
import com.niit.service.cms.dao.SkChannelEnMapper;
import com.niit.service.cms.pojo.SkChannelCn;
import com.niit.service.cms.service.SkChannelCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SkChannelCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/10/30 20:06
 **/
@Service
public class SkChannelCnServiceImpl implements SkChannelCnService {

    @Autowired
    private SkChannelCnMapper skChannelCnMapper;
    @Autowired
    private SkChannelEnMapper skChannelEnMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skChannelCnMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insert(SkChannelCn record) {
        return skChannelCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkChannelCn record,String locale) {
        switch (locale) {
            case "zh":
                return skChannelCnMapper.insertSelective(record);
            case "en":
                return skChannelEnMapper.insertSelective(record);
            default:
                return skChannelCnMapper.insertSelective(record);
        }
    }

    @Override
    public SkChannelCn selectByPrimaryKey(Integer id) {
        return skChannelCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelCn record,String locale) {
        switch (locale) {
            case "zh":
                return skChannelCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skChannelEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skChannelCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKey(SkChannelCn record) {
        return skChannelCnMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SkChannelCn> selectAllChannel(String locale) {
        switch (locale) {
            case "zh":
                return skChannelCnMapper.selectAllChannel();
            case "en":
                return skChannelEnMapper.selectAllChannel();
            default:
                return skChannelCnMapper.selectAllChannel();
        }
    }

    // 批量删除channel,传入的id是String类型
    @Override
    public int deleteMoreChannel(String id,String locale) {
        if ("".equals(id) || id == null) {
            return 0;
        }
        // String --> List
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                return skChannelCnMapper.deleteMoreChannel(list);
            case "en":
                return skChannelEnMapper.deleteMoreChannel(list);
            default:
                return skChannelCnMapper.deleteMoreChannel(list);
        }
    }


    // 根据传入的id(String类型) 转化成 list类型 用以调用dao层方法
    // 添加到工具包中
    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }
}

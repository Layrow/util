package com.niit.service.cms.service;

import com.niit.service.cms.pojo.SkChannelCn;

import java.util.List;

/**
 * @Description SkChannelCnService服务接口
 * @Author liyuhao
 * @Date 2018/10/30 20:01
 **/
public interface SkChannelCnService {

    int deleteByPrimaryKey(Integer id);

    int insert(SkChannelCn record);

    int insertSelective(SkChannelCn record, String locale);

    SkChannelCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelCn record, String locale);

    int updateByPrimaryKey(SkChannelCn record);

    // 查询所有频道
    List<SkChannelCn> selectAllChannel(String locale);

    // 批量删除频道
    int deleteMoreChannel(String id, String locale);

}

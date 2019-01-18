package com.niit.website.cms.service;

import com.niit.website.cms.pojo.SkChannelCn;

import java.util.List;

/**
 * @Description SkChannelCnService服务接口
 * @Author liyuhao
 * @Date 2018/10/30 20:01
 **/
public interface SkChannelCnService {

    int insertSelective(SkChannelCn record, String locale);

    int updateByPrimaryKeySelective(SkChannelCn record, String locale);

    // 查询所有频道
    List<SkChannelCn> selectAllChannel(String locale);

    // 批量删除频道
    int deleteMoreChannel(String id, String locale);
}

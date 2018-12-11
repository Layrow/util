package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkChannelEnMapper {
    int deleteByPrimaryKey(Integer id);

    //int insert(SkChannelCn record);
    int insert(Object o);



    int insertSelective(SkChannelCn record);

    SkChannelCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkChannelCn record);

    int updateByPrimaryKey(SkChannelCn record);
    // 查询所有channel
    List<SkChannelCn> selectAllChannel();
    // 批量删除
    int deleteMoreChannel(List<String> list);
}
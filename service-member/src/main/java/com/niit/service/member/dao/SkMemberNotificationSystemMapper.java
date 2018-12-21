package com.niit.service.member.dao;

import com.niit.service.member.pojo.SkMemberNotificationSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkMemberNotificationSystemMapper {
    /**
     * 显示所有的 系统通知
     * @return
     */
     List<SkMemberNotificationSystem> selectAllSys();

    /**
     * 删除一条系统通知
     * @param id
     * @return
     */
    int deleteByPrimaryKey(List<String> id);

    int insert(SkMemberNotificationSystem record);

    /**
     * 添加一条系统通知
     * @param record
     * @return
     */
    int insertSelective(SkMemberNotificationSystem record);

    SkMemberNotificationSystem selectByPrimaryKey(Integer id);

    /**
     * 修改体统通知
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SkMemberNotificationSystem record);

    int updateByPrimaryKey(SkMemberNotificationSystem record);
}
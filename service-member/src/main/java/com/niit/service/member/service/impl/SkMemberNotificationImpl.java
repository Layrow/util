package com.niit.service.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.service.member.dao.SkMemberNotificationOpsMapper;
import com.niit.service.member.pojo.SkMemberNotificationOps;
import com.niit.service.member.service.ISkMemberNotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
@Service
public class SkMemberNotificationImpl implements ISkMemberNotificationService {

    @Resource
    SkMemberNotificationOpsMapper notificationOpsMapper;
    /**
     * 添加一条通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean insertNotification(SkMemberNotificationOps record) {
        try {
            record.setNoticeTime(new Date());
            if ( notificationOpsMapper.insertSelective(record)>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
            return  false;
    }

    /**
     * 根据通知主键删除通知
     *
     * @param notificationId
     * @return
     */
    @Override
    public boolean deleteNotification(Integer notificationId) {
        try {
            if ( notificationOpsMapper.deleteByPrimaryKey(notificationId)>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return  false;
    }

    /**
     * 查询用户下的所有通知
     *
     * @param userId
     * @return
     */
    @Override
    public PageInfo<SkMemberNotificationOps> listAll(Integer currentPage, Integer pageSize,Integer userId) {
        List<SkMemberNotificationOps> list;
        PageInfo<SkMemberNotificationOps> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        //执行SQL语句（list->分页后的数据）
        list=notificationOpsMapper.selectAll(userId);
        //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
        listInfo = new PageInfo<>(list);
        return  listInfo;
    }
}

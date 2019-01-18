package com.niit.service.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.member.dao.SkMemberNotificationSystemMapper;
import com.niit.service.member.pojo.SkMemberNotificationOps;
import com.niit.service.member.pojo.SkMemberNotificationSystem;
import com.niit.service.member.service.ISkMemberSysNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
public class SkMemberSYSNotificationImpl implements ISkMemberSysNotificationService {

    @Resource
    SkMemberNotificationSystemMapper systemMapper;

    /**
     * 增加一条系统通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean add(SkMemberNotificationSystem record) {
        record.setNoticeTime(new Date());
        try {
            if (systemMapper.insertSelective(record) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 删除一条系统通知
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        try {
            if (systemMapper.deleteByPrimaryKey(Tools.getList(id)) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 修改一条系统通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean update(SkMemberNotificationSystem record) {
        try {
            if (systemMapper.updateByPrimaryKeySelective(record) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 显示所有的 系统通知
     *
     * @return
     */
    @Override
    public PageInfo<SkMemberNotificationSystem> listAll(Integer currentPage, Integer pageSize) {
        List<SkMemberNotificationSystem> list;
        PageInfo<SkMemberNotificationSystem> listInfo;
        PageHelper.startPage(currentPage, pageSize);
        //执行SQL语句（list->分页后的数据）
        list = systemMapper.selectAllSys();
        //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
        listInfo = new PageInfo<>(list);
        return listInfo;
    }
}

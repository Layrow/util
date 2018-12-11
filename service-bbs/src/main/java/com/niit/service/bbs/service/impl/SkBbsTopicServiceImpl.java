package com.niit.service.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.BadWordUtil;
import com.niit.common.utils.Tools;
import com.niit.service.bbs.dao.SkBbsTopicMapper;
import com.niit.service.bbs.pojo.SkBbsTopic;
import com.niit.service.bbs.service.SkBbsTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/4
 * @since 1.0.0
 */
@Service
public class SkBbsTopicServiceImpl implements SkBbsTopicService {

    @Resource
    SkBbsTopicMapper skBbsTopicMapper;

    @Override
    public int updateTopic(SkBbsTopic record) {
        return skBbsTopicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(SkBbsTopic record) {
        record.setCreateTime(new Date());
        if (BadWordUtil.isContaintBadWord(record.getTitle(), 2)){
            record.setTitle(BadWordUtil.replaceBadWord(record.getTitle(),2,"*"));
        }
        if (BadWordUtil.isContaintBadWord(record.getContent(), 2)){
            record.setContent(BadWordUtil.replaceBadWord(record.getContent(),2,"*"));
        }
        record.setStatus(1);
        return skBbsTopicMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skBbsTopicMapper.deleteByPrimaryKey(id);
    }

    /**
     * 管理员批量删除帖子
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByPrimaryKeyList(List<String> id) {
        return skBbsTopicMapper.deleteByPrimaryKeyList(id)>0;
    }

    @Override
    public int updateViewCountByPrimaryKey(Integer id, Integer newCount) {
        return skBbsTopicMapper.updateViewCountByPrimaryKey(id,newCount);
    }

    @Override
    public PageInfo<SkBbsTopic> listAllTopic(Integer currentPage, Integer pageSize) {

        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        //执行SQL语句（list->分页后的数据）
        list=skBbsTopicMapper.listAllTopic();
        //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
        listInfo = new PageInfo<>(list);
        return  listInfo;
    }

    @Override
    public PageInfo<SkBbsTopic> listAllTopicInSection(Integer currentPage, Integer pageSize, Integer sectionId) {
        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> pageInfo;
        PageHelper.startPage(currentPage,pageSize);
        list=skBbsTopicMapper.listAllTopicInSection(sectionId);
        pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<SkBbsTopic> likeSelectAll(Integer currentPage, Integer pageSize, String title,String key) {
        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> pageInfo;
        PageHelper.startPage(currentPage,pageSize);
        list=skBbsTopicMapper.likeSelectAll(title,key);
        pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<SkBbsTopic> listAllTopicByOwner(Integer currentPage, Integer pageSize, String userId) {
        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> pageInfo;
        PageHelper.startPage(currentPage,pageSize);
        list=skBbsTopicMapper.listAllTopicByOwner(userId);
        pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean check(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doCheck(Tools.getList(id));
        }
    }

    /**
     * 批量设置官宣
     *
     * @param id
     * @return
     */
    @Override
    public boolean offcial(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doOffcial(Tools.getList(id));
        }
    }

    @Override
    public boolean top(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doTop(Tools.getList(id));
        }
    }

    @Override
    public boolean essence(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doEssence(Tools.getList(id));
        }
    }

    @Override
    public boolean unCheck(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doUnCheck(Tools.getList(id));
        }
    }

    @Override
    public boolean unTop(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doUnTop(Tools.getList(id));
        }
    }

    @Override
    public boolean unEssence(String id) {
        if (id==null||"".equals(id)){
            return  false;
        }else {
            return skBbsTopicMapper.doUnEssence(Tools.getList(id));
        }
    }
}
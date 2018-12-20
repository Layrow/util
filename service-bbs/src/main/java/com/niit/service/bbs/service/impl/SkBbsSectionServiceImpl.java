package com.niit.service.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.bbs.dao.SkBbsReplyMapper;
import com.niit.service.bbs.dao.SkBbsSectionMapper;
import com.niit.service.bbs.dao.SkBbsTopicMapper;
import com.niit.service.bbs.pojo.SkBbsReply;
import com.niit.service.bbs.pojo.SkBbsSection;
import com.niit.service.bbs.service.SkBbsSectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/3
 * @since 1.0.0
 */
@Service
public class SkBbsSectionServiceImpl implements SkBbsSectionService {
    @Resource
    SkBbsSectionMapper skBbsSectionMapper;
    @Resource
    SkBbsTopicMapper skBbsTopicMapper;
    @Resource
    SkBbsReplyMapper skBbsReplyMapper;

    /**
     * 根据 栏目id查询栏目名称
     *
     * @param sectionId
     * @return
     */
    @Override
    public String selectName(Integer sectionId) {
        return skBbsSectionMapper.selectNameForEdit(sectionId);
    }

    @Override
    public boolean addSection(SkBbsSection section) {
        section.setCreateDate(new Date());
        return skBbsSectionMapper.insertSelective(section)>0;
    }

    @Override
    public boolean deleteSection(String id)
    {
       try {
           skBbsReplyMapper.deleteReplyBySection(Tools.getList(id));
           skBbsTopicMapper.deleteBySection(Tools.getList(id));
           skBbsSectionMapper.deleteByPrimaryKey(Tools.getList(id));
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return  false;
       }
    }

    @Override
    public boolean updateOderBatch(List<SkBbsSection> list) {
        //如果更新的数量和传入的对象数量一致则返回true,失败返回false
        return skBbsSectionMapper.doBatchUpdateSectionOrder(list)==list.size();
    }

    @Override
    public List<SkBbsSection> selectAllSection() {
        return skBbsSectionMapper.doSelectAllInOrder();
    }

    @Override
    public boolean editSection(SkBbsSection section) {
        return skBbsSectionMapper.updateByPrimaryKeySelective(section)>0;
    }
    /**
     * 分页模糊查询
     *
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<SkBbsSection> likeSelectAll(Integer currentPage, Integer pageSize, String name) {
        List<SkBbsSection> list;
        PageInfo<SkBbsSection> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        //执行SQL语句（list->分页后的数据）
        list=skBbsSectionMapper.likeSelectAll(name);
        //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
        listInfo = new PageInfo<>(list);
        return  listInfo;
    }
}

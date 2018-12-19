package com.niit.service.bbs.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.BadWordUtil;
import com.niit.common.utils.Tools;
import com.niit.service.bbs.dao.SkBbsReplyMapper;
import com.niit.service.bbs.dao.SkBbsTopicMapper;
import com.niit.service.bbs.pojo.SkBbsReply;
import com.niit.service.bbs.pojo.SkBbsTopic;
import com.niit.service.bbs.service.SkBbsTopicService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    @Resource
    private SkBbsReplyMapper skBbsReplyMapper;

    @Override
    public Map<Integer, List<SkBbsTopic>> selectAllTopicById() {
        //查询出所有回帖
        List<SkBbsReply> skBbsReplies = skBbsReplyMapper.selectAll();
        //查出所有回帖的ID
        LinkedList<Integer> list = new LinkedList<>();
        skBbsReplies.stream().forEach(e->list.add(e.getId()));
        //根据回帖Id查询出相对应的帖子
        //List<SkBbsTopic> topics = skBbsTopicMapper.selectAllTopicById(list);
        Map<Integer, List<SkBbsTopic>> map=new LinkedHashMap<>();
        skBbsReplies.stream().forEach(e->{
            //key放入 回帖Id  value放入回帖对映的帖子
            map.put(e.getId(),skBbsReplyMapper.selectAllTopicById(e.getId()));

        });
        return map;


    }

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

    /**
     * 分页显示所有带脏词的帖子
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> selectAllDirty(Integer currentPage, Integer pageSize) {
        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> pageInfo;
        PageHelper.startPage(currentPage,pageSize);
        list=skBbsTopicMapper.selectAllDirty();
        pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 分页 模糊查询指定栏目下的帖子
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param title       查询关键字
     * @param key
     * @param sectionId
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> likeSectionAll(Integer currentPage, Integer pageSize, String title, Integer sectionId,String key) {
        List<SkBbsTopic> list;
        PageInfo<SkBbsTopic> pageInfo;
        PageHelper.startPage(currentPage,pageSize);
        list=skBbsTopicMapper.likeSelectInSection(title,sectionId,key);
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

    /**
     * 根据Id取得每个帖子
     *
     * @param id
     * @return
     */
    @Override
    public SkBbsTopic getTopic(Integer id) {
        return skBbsTopicMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<Integer, List<Object>> selectReplyUserId(String replyUserId) {
        //查询出该用户的所有贴子
        List<SkBbsTopic> list = skBbsTopicMapper.selectReplyUserId(replyUserId);
        LinkedList<Integer> topicId = new LinkedList<>();
        list.stream().forEach(e->topicId.add(e.getId()));
        //根据帖子id查出所有所有回复
        List<SkBbsReply> replies = skBbsReplyMapper.selectReplyByIds(topicId);
        Map<Integer, List<Object>> totalResult=new LinkedHashMap<>();
        list.stream().forEach(e->{
            //tempList用于存放回复总量、最新回复
            List<Object> tempList=new LinkedList<>();
            //找到与帖子ID对应的所有回复
            List<SkBbsReply> matchedReplys=replies.stream().filter(p->p.getTopicId().equals(e.getId())).collect(Collectors.toList());
            //运算出回复总量
            Integer replysCount=matchedReplys.size();
            //运算出最新回复实体
            SkBbsReply lastedReply;
            if(replysCount>0) {
                lastedReply= matchedReplys.stream().sorted(Comparator.comparing(SkBbsReply::getReplyTime).reversed()).findFirst().get();
            }else{
                lastedReply=null;
            }
            //把回复总量、最新回复加入tempList
            tempList.add(replysCount);
            tempList.add(lastedReply);

            //把tempList放入结果集，key是topicId
            totalResult.put(e.getId(),tempList);
            //totalResult.put(e.getId(),skBbsTopicMapper.selectReplyUserId(replyUserId));
        });
        return totalResult;

    }

    @Override
    public PageInfo<SkBbsTopic> selectReplyUserIds(Integer currentPage, Integer pageSize, String replyUserId) {
        PageInfo<SkBbsTopic>  pageInfo = null;
        PageHelper.startPage(currentPage,pageSize);
        List<SkBbsTopic> list = skBbsTopicMapper.selectReplyUserIds(replyUserId);
        pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}

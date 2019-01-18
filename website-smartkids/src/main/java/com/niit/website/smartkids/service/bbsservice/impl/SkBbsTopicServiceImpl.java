package com.niit.website.smartkids.service.bbsservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.website.smartkids.pojo.bbs.SkBbsTopic;
import com.niit.website.smartkids.service.bbsservice.SkBbsTopicService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/6
 * @since 1.0.0
 */
@Service
public class SkBbsTopicServiceImpl implements SkBbsTopicService {

    private static final String SERVICE_NAME = "service-bbs";
    private static final String URL = "http://" + SERVICE_NAME + "/topic/";

    @Resource
    RestTemplate restTemplate;

    @Override
    public SkBbsTopic getTopic(Integer id) {
        return restTemplate.getForObject(URL + "get?id=" + id, SkBbsTopic.class);
    }

    @Override
    public String selectAllTopicById() {

        return restTemplate.getForObject("http://" + SERVICE_NAME + "/topic/allTopic", String.class);
    }

    @Override
    public List<String> listUserId(String id) {
        return restTemplate.postForObject(URL + "/listUserId?id=" + id, null, List.class);
    }

    @Override
    public boolean editOne(SkBbsTopic record) {
        try {
            restTemplate.put(URL + "edit", record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 客户端发表帖子
     *
     * @param record 帖子对象
     * @return
     */
    @Override
    public boolean insertSelective(SkBbsTopic record) {

        String result =
                restTemplate.postForObject(URL + "add", record, String.class);
        if (Tools.isEmpty(result)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除帖子根据帖子的主键Id
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            restTemplate.delete(URL + "delete?id=" + id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除帖子根据帖子的主键Ids
     *
     * @param ids
     * @return
     */
    @Override
    public boolean batchDel(String ids) {
        try {
            restTemplate.delete(URL + "batchDel?ids=" + ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新帖子浏览量
     *
     * @param id 帖子主键
     * @return
     */
    @Override
    public int updateViewCountByPrimaryKey(Integer id) {
        try {
            restTemplate.put(URL + "editViewCount?id=" + id, String.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 分页显示所有的 帖子
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> listAllTopic(Integer currentPage, Integer pageSize) {
        return restTemplate.postForObject(URL + "listAll?currentPage=" + currentPage + "&pageSize=" + pageSize, null, PageInfo.class);
    }

    /**
     * 分页显示指定栏目下的帖子
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param sectionId   指定栏目Id
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> listAllTopicInSection(Integer currentPage, Integer pageSize, Integer sectionId) {
        return restTemplate.postForObject(URL + "listSection?currentPage=" + currentPage + "&pageSize=" + pageSize + "&sectionId=" + sectionId, null, PageInfo.class);
    }

    /**
     * 分页 模糊查询
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param title       查询关键字
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> likeSelectAll(Integer currentPage, Integer pageSize, String title, String key) {
        return restTemplate.postForObject(URL + "likeList?currentPage=" + currentPage + "&pageSize=" + pageSize + "&title=" + title + "&key=" + key, null, PageInfo.class);
    }

    /**
     * 分页 显示 带脏词的帖子
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> selectAllDirty(Integer currentPage, Integer pageSize) {
        return restTemplate.postForObject(URL + "dirty?currentPage=" + currentPage + "&pageSize=" + pageSize, null, PageInfo.class);
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param title
     * @param sectionId
     * @param key
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> likeSection(Integer currentPage, Integer pageSize, String title, Integer sectionId, String key) {
        return restTemplate.postForObject(URL + "likeSection?currentPage=" + currentPage + "&pageSize="
                + pageSize + "&title=" + title + "&sectionId=" + sectionId + "&key=" + key, null, PageInfo.class);
    }


    /**
     * 分页查询,指定用户的所有发帖
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param userId      指定的用户Id
     * @return
     */
    @Override
    public PageInfo<SkBbsTopic> listAllTopicByOwner(Integer currentPage, Integer pageSize, String userId) {
        return restTemplate.postForObject(URL + "listUser?currentPage=" + currentPage + "&pageSize=" + pageSize + "&userId=" + userId, null, PageInfo.class);
    }

    /**
     * 批量审核 帖子
     *
     * @param id 审核的帖子的集合
     * @return
     */
    @Override
    public boolean check(String id) {
        try {
            restTemplate.put(URL + "check?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量置顶
     *
     * @param id
     * @return
     */
    @Override
    public boolean top(String id) {
        try {
            restTemplate.put(URL + "top?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量加精
     *
     * @param id
     * @return
     */
    @Override
    public boolean essence(String id) {
        try {
            restTemplate.put(URL + "essence?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量官宣
     *
     * @param id
     * @return
     */
    @Override
    public boolean offcial(String id) {
        try {
            restTemplate.put(URL + "offcial?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量审核 帖子
     *
     * @param id 审核的帖子的集合
     * @return
     */
    @Override
    public boolean unCheck(String id) {
        try {
            restTemplate.put(URL + "unCheck?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量置顶
     *
     * @param id
     * @return
     */
    @Override
    public boolean unTop(String id) {
        try {
            restTemplate.put(URL + "unTop?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量加精
     *
     * @param id
     * @return
     */
    @Override
    public boolean unEssence(String id) {
        try {
            restTemplate.put(URL + "unEssence?id=" + id, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PageInfo<SkBbsTopic> selectReplyUserIds(Integer currentPage, Integer pageSize, String replyUserId) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/topic?currentPage=" + currentPage + "&pageSize=" + pageSize + "&replyUserId=" + replyUserId, PageInfo.class);
    }

    @Override
    public String selectReplyUserId(String replyUserId) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/topic/replyId?replyUserId=" + replyUserId, String.class);
    }

}

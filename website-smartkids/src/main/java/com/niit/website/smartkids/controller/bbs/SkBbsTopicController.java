package com.niit.website.smartkids.controller.bbs;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.website.smartkids.enums.IntegralActionsEnum;
import com.niit.website.smartkids.pojo.bbs.SkBbsTopic;
import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.service.bbsservice.SkBbsTopicService;
import com.niit.website.smartkids.service.memberservice.IMemberItegralService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/6
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/topic")
public class SkBbsTopicController {
    ;
    @Resource
    SkBbsTopicService skBbsTopicService;
    @Resource
    IMemberItegralService memberItegralService;


    @GetMapping("/getOne")
    public SkBbsTopic getTopic(@RequestParam Integer id) {
        return skBbsTopicService.getTopic(id);
    }

    /**
     * 单个修改帖子
     *
     * @param record
     * @return
     */
    @PutMapping("editOne")
    public boolean editOne(@RequestBody SkBbsTopic record) {
        return skBbsTopicService.editOne(record);
    }

    /**
     * 增加帖子
     *
     * @param topic
     * @return
     */
    @PostMapping("/add")
    public boolean insert(@RequestBody SkBbsTopic topic, @RequestParam Integer uid) {
        SkMemberIntegral integral = new SkMemberIntegral();
        integral.setUserId(uid);
        integral.setActions(IntegralActionsEnum.POST_TOPIC.getAction());
        integral.setOperation(IntegralActionsEnum.POST_TOPIC.getOperation());
        integral.setNumbers(IntegralActionsEnum.POST_TOPIC.getNums());
        memberItegralService.interAction(integral);
        return skBbsTopicService.insertSelective(topic);
    }

    /**
     * 删除帖子
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Integer id) {
        return skBbsTopicService.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 修改帖子的浏览量
     *
     * @param id
     * @return
     */
    @PutMapping("/editViewCount")
    public boolean editViewCount(@RequestParam Integer id) {
        return skBbsTopicService.updateViewCountByPrimaryKey(id) > 0;
    }

    /**
     * 全部查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/listAll")
    public PageInfo<SkBbsTopic> listAll(@RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        return skBbsTopicService.listAllTopic(currentPage, pageSize);
    }

    /**
     * 全部查询脏词贴
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/dirty")
    public PageInfo<SkBbsTopic> dirty(@RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        return skBbsTopicService.selectAllDirty(currentPage, pageSize);
    }

    /**
     * 根据栏目类别显示
     *
     * @param currentPage
     * @param pageSize
     * @param sectionId
     * @return
     */
    @PostMapping("/listSection")
    public PageInfo<SkBbsTopic> listSection(@RequestParam Integer currentPage, @RequestParam Integer pageSize, @RequestParam Integer sectionId) {
        return skBbsTopicService.listAllTopicInSection(currentPage, pageSize, sectionId);
    }

    /**
     * 模糊查询所有
     *
     * @param currentPage
     * @param pageSize
     * @param title
     * @return
     */
    @PostMapping("/likeList")
    public PageInfo<SkBbsTopic> likeList(@RequestParam Integer currentPage, @RequestParam Integer pageSize,
                                         @RequestParam String title, @RequestParam String key) {
        return skBbsTopicService.likeSelectAll(currentPage, pageSize, title, key);
    }

    /**
     * 模糊查询指定栏目下所有
     *
     * @param currentPage
     * @param pageSize
     * @param title
     * @return
     */
    @PostMapping("/likeSection")
    public PageInfo<SkBbsTopic> likeList(@RequestParam Integer currentPage, @RequestParam Integer pageSize,
                                         @RequestParam String title, @RequestParam Integer sectionId, @RequestParam String key) {
        return skBbsTopicService.likeSection(currentPage, pageSize, title, sectionId, key);
    }

    /**
     * 根据用户Id来查询用户下的所有 帖子
     *
     * @param currentPage
     * @param pageSize
     * @param userId      用户Id
     * @return
     */
    @PostMapping("/listUser")
    public PageInfo<SkBbsTopic> listUser(@RequestParam Integer currentPage, @RequestParam Integer pageSize, @RequestParam String userId) {
        return skBbsTopicService.listAllTopicByOwner(currentPage, pageSize, userId);
    }

    /**
     * 批量审查
     *
     * @param id
     * @return
     */
    @PutMapping("/check")
    public boolean check(@RequestParam String id) {
        return skBbsTopicService.check(id);
    }

    /**
     * 批量官宣
     *
     * @param id
     * @return
     */
    @PutMapping("/offcial")
    public boolean offcial(@RequestParam String id) {
        return skBbsTopicService.offcial(id);
    }

    /**
     * 批量置顶
     *
     * @param id
     * @return
     */
    @PutMapping("/top")
    public boolean top(@RequestParam String id) {
        List<Integer> ids = new ArrayList<>();
        List<String> list = skBbsTopicService.listUserId(id);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            ids.add(Integer.parseInt(iterator.next()));
        }
        Integer[] tids = (Integer[]) ids.toArray(new Integer[ids.size()]);
        SkMemberIntegral integral = null;
        for (Integer item : tids) {
            integral = new SkMemberIntegral();
            integral.setUserId(item);
            integral.setActions(IntegralActionsEnum.POST_TOP.getAction());
            integral.setOperation(IntegralActionsEnum.POST_TOP.getOperation());
            integral.setNumbers(IntegralActionsEnum.POST_TOP.getNums());
            memberItegralService.interAction(integral);
        }
        return skBbsTopicService.top(id);
    }


    /**
     * 批量加精
     *
     * @param id 需要被加精的字符串用","分割.
     * @return
     */
    @PutMapping("/essence")
    public boolean essence(@RequestParam String id) {
        List<Integer> ids = new ArrayList<>();
        List<String> list = skBbsTopicService.listUserId(id);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            ids.add(Integer.parseInt(iterator.next()));
        }
        Integer[] tids = (Integer[]) ids.toArray(new Integer[ids.size()]);
        SkMemberIntegral integral = null;
        for (Integer item : tids) {
            integral = new SkMemberIntegral();
            integral.setUserId(item);
            integral.setActions(IntegralActionsEnum.POST_ESSENCE.getAction());
            integral.setOperation(IntegralActionsEnum.POST_ESSENCE.getOperation());
            integral.setNumbers(IntegralActionsEnum.POST_ESSENCE.getNums());
            memberItegralService.interAction(integral);
        }
        return skBbsTopicService.essence(id);
    }

    /**
     * 批量审查,使帖子通过,默认帖子是通过.
     *
     * @param id
     * @return
     */
    @PutMapping("/unCheck")
    public boolean unCheck(@RequestParam String id) {
        return skBbsTopicService.unCheck(id);
    }

    /**
     * 取消置顶
     *
     * @param id
     * @return
     */
    @PutMapping("/unTop")
    public boolean unTop(@RequestParam String id) {
        return skBbsTopicService.unTop(id);
    }

    /**
     * 取消加精
     *
     * @param id id集合
     * @return
     */
    @PutMapping("/unEssence")
    public boolean unEssence(@RequestParam String id) {
        return skBbsTopicService.unEssence(id);
    }

    @DeleteMapping("batchDel")
    public boolean batchDel(@RequestParam String ids) {
        return skBbsTopicService.batchDel(ids);
    }

    /**
     * 功能描述: 查询该用户回复的所有帖
     *
     * @return
     * @author huangwei
     * @date :2018/12/14
     * @params
     */
    @GetMapping
    public PageInfo<SkBbsTopic> selectAll(@RequestParam Integer currentPage, @RequestParam Integer pageSize, String replyUserId) {
        PageInfo<SkBbsTopic> pageInfo = skBbsTopicService.selectReplyUserIds(currentPage, pageSize, replyUserId);
        return pageInfo;
    }

    @GetMapping("/replyId")
    public Map<Integer, List<Object>> selectReplyUserIdAll(String replyUserId) {
        String s = skBbsTopicService.selectReplyUserId(replyUserId);
        Map<Integer, List<Object>> map = null;
        Type type = new TypeToken<Map<Integer, List<Object>>>() {
        }.getType();
        Gson gson = new Gson();
        if (s == null || s.isEmpty()) {
            return map;
        }
        map = gson.fromJson(s, type);
        return map;
    }

    @GetMapping("/allTopic")
    public Map<Integer, String> selectAllTopic() {
        String s = skBbsTopicService.selectAllTopicById();
        Map<Integer, String> map = null;
        Type type = new TypeToken<Map<Integer, String>>() {
        }.getType();
        Gson gson = new Gson();
        if (s.isEmpty() || s == null) {
            return map;
        }
        map = gson.fromJson(s, type);
        return map;

    }

}

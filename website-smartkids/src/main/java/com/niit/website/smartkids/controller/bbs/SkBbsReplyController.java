package com.niit.website.smartkids.controller.bbs;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.website.smartkids.pojo.bbs.SkBbsReply;
import com.niit.website.smartkids.service.bbsservice.SkBbsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * @Auther: huangwei
 * @Date:2018/12/04 10:57
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/reply")
public class SkBbsReplyController {

    @Autowired
    private SkBbsReplyService skBbsReplyService;

    /**
     * 回帖信息
     * @param sectionId
     * @return
     */
    @PostMapping("/replyInfo")
    public Map<Integer, List<Object>> replyInfo(@RequestParam  Integer sectionId){
        String s = skBbsReplyService.replyInfo(sectionId);
        Map<Integer, List<Object>> map = null;
        Type type = new TypeToken< Map<Integer, List<Object>>>() {
        }.getType();
        Gson gson = new Gson();
        if(s==null||s.isEmpty()){
            return map;
        }
        map= gson.fromJson(s,type);
        return map;

    }
    /**
    * 功能描述:添加一个回帖  * 测试成功
    * @author huangwei
    * @date :2018/12/04
    * @params [skBbsReply]
    * @return int
    */
    @PostMapping
    public int insertSelective(@RequestBody SkBbsReply record) {
        int i = skBbsReplyService.insertSelective(record);
        return i;


    }
    /**
    * 功能描述:删除自己回复的评论
    * @author huangwei
    * @date :2018/12/04
    * @params [id]
    * @return int
    */
    @DeleteMapping
    public  int deletePrimaryKey(Integer id){
        return  skBbsReplyService.deleteByPrimaryKey(id);
    }
    /**
    * 功能描述:查询帖子的所有回复帖分页（排序）   *  测试成功
    * @author huangwei
    * @date :2018/12/04
    * @params [topicId]
    * @return java.util.List<com.niit.service.bbs.pojo.SkBbsReply>
    */
    @GetMapping
    public PageInfo<SkBbsReply> selectAllReply(Integer topicId,Integer currentPage,Integer pageSize){
        PageInfo<SkBbsReply> pageInfo = skBbsReplyService.selectAllReply(topicId, currentPage, pageSize);
        return pageInfo;
    }
    @PostMapping("/dirtyReply")
    public PageInfo<SkBbsReply> dirtyReply(Integer currentPage,Integer pageSize){
        PageInfo<SkBbsReply> pageInfo = skBbsReplyService.selectAllDirtyReply( currentPage, pageSize);
        return pageInfo;
    }
    /**
    * 功能描述:查询所有回复数    *测试成功
    * @author huangwei
    * @date :2018/12/04
    * @params [topicId]
    * @return int
    */
    @GetMapping("/count")
    public String selectCount(Integer topicId){

        String s = skBbsReplyService.selectCount(topicId);
        return  s;

    }
    /**
    * 功能描述:获取最近回复时间和回复人  *  测试成功
    * @author huangwei
    * @date :2018/12/05
    * @params [topicId]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    @GetMapping("/date")
    public Map<String, Object> selectDate(Integer topicId){
        Map<String, Object> map = skBbsReplyService.selectDate(topicId);
        return map;
    }
    /**
    * 功能描述:批量删除回帖   *测试成功
    * @author huangwei
    * @date :2018/12/05
    * @params [id]
    * @return java.lang.String
    */
    @DeleteMapping("/batch")
    public  String  deleteAd(HttpServletRequest request){
        String id = request.getParameter("id");
        skBbsReplyService.deleteAd(id);
        return "删除成功";

    }
    /**
    * 功能描述:查询所有回复帖  *  成功
    * @author huangwei
    * @date :2018/12/05
    * @params []
    * @return java.util.List<com.niit.service.bbs.pojo.SkBbsReply>
    */
    @GetMapping("/all")
    public PageInfo<SkBbsReply> selectAll(Integer currentPage, Integer pageSize){
        PageInfo<SkBbsReply> pageInfo = skBbsReplyService.selectAll(currentPage, pageSize);
        return  pageInfo;
    }
    /**
     * 功能描述:编辑修改
     * @author huangwei
     * @date :2018/12/10
     * @params [record]
     * @return int
     */
    @PutMapping
    public  int updateSelective(@RequestBody SkBbsReply record){
        return skBbsReplyService.updateByPrimaryKeySelective(record);
    }
    /**
     * 功能描述:批量审核
     * @author huangwei
     * @date :2018/12/10
     * @params [id]
     * @return void
     */
    @PutMapping("/batch")
    public void  updateAd(String id){
        skBbsReplyService.updateAd(id);

    }
    /**
     * 功能描述:插询所有已经审核的方法
     * @author huangwei
     * @date :2018/12/11
     * @params []
     * @return java.util.List<com.niit.service.bbs.pojo.SkBbsReply>
     */
    @GetMapping("/status")
    public PageInfo<SkBbsReply>  selectAllStatus(Integer currentPage, Integer pageSize){
        PageInfo<SkBbsReply> skBbsReplies = skBbsReplyService.selectAllStatus(currentPage,pageSize);
        return skBbsReplies;

    }
    @GetMapping("/NoStatus")
    public PageInfo<SkBbsReply>  selectAllNoStatus(Integer currentPage, Integer pageSize){
        return skBbsReplyService.selectAllNoStatus( currentPage,pageSize);
    }
    @GetMapping("/reply")
    public PageInfo<SkBbsReply> selectReply(Integer currentPage, Integer pageSize, String replyUserId) {
        PageInfo<SkBbsReply> pageInfo = skBbsReplyService.selectReply(currentPage, pageSize, replyUserId);
        return pageInfo;
    }
    @GetMapping("/replyAll")
    public  Map<Integer, List<Object>>  selectAll(){
        String s = skBbsReplyService.selectAll();
        Map<Integer,List<Object>> map = null;
        Type type = new TypeToken<Map<Integer,List<Object>>>(){}.getType();
        Gson gson = new Gson();
        if(s.isEmpty()||s==null){
            return map;
        }
        map = gson.fromJson(s,type);
        return map;
    }

}

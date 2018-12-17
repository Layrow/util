package com.niit.service.bbs.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.bbs.pojo.SkBbsReply;
import com.niit.service.bbs.service.SkBbsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    * 功能描述:查询一个版块下的所有回复
    * @author huangwei
    * @date :2018/12/17
    * @params [currentPage, pageSize, sectionId]
    * @return java.lang.String
    */
    @PostMapping("/replyInfo")
    public String replyInfo(@RequestParam  Integer sectionId){
        Map<Integer, List<Object>> map = skBbsReplyService.replyInfo( sectionId);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);

    }
    /**
    * 功能描述:添加一个回帖 *  成功
    * @author huangwei
    * @date :2018/12/04
    * @params [skBbsReply]
    * @return int
    */
    @PostMapping
    public int insertSelective(@RequestBody SkBbsReply skBbsReply) {
        int i = skBbsReplyService.insertSelective(skBbsReply);
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
    * 功能描述:查询帖子的所有回复帖分页（排序）
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
    /**
    * 功能描述:查询所有回复数
    * @author huangwei
    * @date :2018/12/04
    * @params [topicId]
    * @return int
    */
    @GetMapping("/count")
    public String selectCount(Integer topicId){
        return skBbsReplyService.selectCount(topicId);

    }
    /**
    * 功能描述:获取最近回复时间和回复人
    * @author huangwei
    * @date :2018/12/05
    * @params [topicId]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    @GetMapping("/date")
    public Map<String, Object> selectDate(Integer topicId){
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map = skBbsReplyService.selectDate(topicId);
        Object o = map.get("time");
        Object o1 = map.get("reply_userid");
        Date date = (Date) o;
        String str = (String) o1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        map1.put("times",format);
        map1.put("user",str);
        return map1;
    }
    /**
    * 功能描述:批量删除回帖
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
    * 功能描述:查询所有回复帖
    * @author huangwei
    * @date :2018/12/05
    * @params []
    * @return java.util.List<com.niit.service.bbs.pojo.SkBbsReply>
    */
    @GetMapping("/all")
    public PageInfo<SkBbsReply>  selectAll(Integer currentPage,Integer pageSize){
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
    public PageInfo<SkBbsReply>  selectReply(Integer currentPage, Integer pageSize,String replyUserId){
        PageInfo<SkBbsReply> pageInfo = skBbsReplyService.selectReply(currentPage, pageSize, replyUserId);
        return  pageInfo;
    }
    @GetMapping("/replyAll")
    public  String  selectAll(){
        Map<Integer, List<Object>> map = skBbsReplyService.selectAll();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return  gson.toJson(map);

    }
}

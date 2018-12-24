package com.niit.service.cms.controller;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleVideoCn;
import com.niit.service.cms.service.SkChannelArticleVideoCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 功能描述:视频内容
 *
 * @author huangwei
 * @date 2018/11/1
 */
@CrossOrigin
@RestController
@RequestMapping("/video")
public class SkChannelArticleVideoCnController {
    @Autowired
    private SkChannelArticleVideoCnService skChannelArticleVideoCnService;

    /**
     * 功能描述: 分页模糊查询
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleVideoCn>
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleVideoCn> likeSelectAllInfo(@Param("categoryId") Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleVideoCn> listInfo=null;
        try {
            listInfo = skChannelArticleVideoCnService.likeSelectAll(currentPage,pageSize,title,locale,categoryId,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    /**
     * 功能描述:插入
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     * @return java.lang.String
     */
    @PostMapping
    public SkChannelArticleVideoCn insertSelectiveInfo(@RequestBody SkChannelArticleVideoCn record,@RequestParam String locale) {
        record.setAddTime(new Date());

        skChannelArticleVideoCnService.insertSelective(record,locale);
         return  record;

    }
    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleVideoCn> lis, String locale){
        System.out.println("------"+locale+lis+"-----");
        int i = skChannelArticleVideoCnService.batchUp(lis, locale);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);
    }
    /**
     * 功能描述:删除
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     * @return java.lang.String
     */
   /* @DeleteMapping("/delete")
    public String deleteByPrimaryKeyInfo(Integer id) {
        int i = skChannelArticleVideoCnService.deleteByPrimaryKey(id);
        if (i > 0) {
            return "删除成功";
        } else
            return "删除失败";
    }*/
    /**
     * 功能描述:查找根据ID
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     * @return com.niit.service.cms.pojo.SkChannelArticleVideoCn
     */
    @GetMapping("/id")
    public SkChannelArticleVideoCn selectByPrimaryKeyInfo(@RequestParam Integer id,@RequestParam String locale) {
        SkChannelArticleVideoCn contentCn = skChannelArticleVideoCnService.selectByPrimaryKey(id,locale);
        return contentCn;
    }
    /**
     * 功能描述:查找所有
     * @author huangwei
     * @date 2018/11/1
     * @params []
     * @return java.util.List<com.niit.service.cms.pojo.SkChannelArticleVideoCn>
     */
//    @GetMapping
//    public PageInfo<SkChannelArticleVideoCn> selectAllInfo(int currentPage, int pageSize) {
//        PageInfo<SkChannelArticleVideoCn> listInfo=null;
//        try {
//            listInfo = skChannelArticleVideoCnService.selectAll(currentPage,pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listInfo;
//    }

    @GetMapping
    public PageInfo<SkChannelArticleVideoCn> selectAllInfo(String key,int currentPage, int pageSize,@RequestParam String locale) {
        PageInfo<SkChannelArticleVideoCn> listInfo = null;
        try {
            listInfo=skChannelArticleVideoCnService.FuzzySearchBy(key,currentPage,pageSize,locale);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    /**
     * 功能描述:更新
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     * @return java.lang.String
     */
    @PutMapping
    public SkChannelArticleVideoCn updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleVideoCn record,@RequestParam String locale) {
        record.setUpdateTime(new Date());

        skChannelArticleVideoCnService.updateByPrimaryKeySelective(record,locale);
        return record;

    }
    /**
     * 功能描述:批量删除
     * @author huangwei
     * @date 2018/11/1
     * @params [request]
     * @return java.lang.String
     */
    @DeleteMapping
    public String deleteAd(HttpServletRequest request,@RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.deleteAd(id,locale);
        return id;
    }
    /**
     * 功能描述:批量审核
     * @author huangwei
     * @date 2018/11/1
     * @params [request]
     * @return java.lang.String
     */
    @PutMapping("/review")
    public  String updateSt(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateSt(id,locale);
        return "批量审核";

    }
    @PutMapping("/top")
    public  String updateTo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateTo(id,locale);
        return "批量置顶";
    }
    @PutMapping("/red")
    public  String updateRe(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateRe(id,locale);
        return "批量推荐";
    }
    @PutMapping("/hot")
    public  String updateHo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateHo(id,locale);
        return "批量热门";
    }

//    /**
//     * 功能描述:分页模糊查询
//     * @author huangwei
//     * @date 2018/11/8
//     * @params [currentPage, pageSize, title]
//     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleVideoCn>
//     */
//    @PostMapping("/titles")
//    public PageInfo<SkChannelArticleVideoCn> likeSelectAllInfo(int currentPage, int pageSize, String title,@RequestParam String locale) {
//        PageInfo<SkChannelArticleVideoCn> listInfo=null;
//        try {
//            listInfo = skChannelArticleVideoCnService.likeSelectAll(currentPage,pageSize,title,locale);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listInfo;
//    }

    // 按照栏目类别ID并且status = 1查找新闻
    @GetMapping("/{locale}/{categoryId}")
    public String selectNewsByCategoryId(@PathVariable("locale") String locale, @PathVariable("categoryId") Integer categoryId,
                                         Integer currentPage, Integer pageSize) {
        String info = null;
        try {
            info = skChannelArticleVideoCnService.selectVideoByCategoryId(locale, categoryId,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }


}

package com.niit.service.cms.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleContentCn;
import com.niit.service.cms.service.SkChannelArticleContentCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 功能描述:关于我们内容
 *
 * @author huangwei
 * @date 2018/11/1
 */
@RestController
@RequestMapping("/content")
public class SkchannelArticleContentCnController {

    @Autowired
    private SkChannelArticleContentCnService skChannelArticleContentCnService;

    /**
     * 功能描述: 分页模糊查询
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleContentCn>
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleContentCn> likeSelectAllInfo(@Param("categoryId") Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleContentCn> listInfo=null;
        try {
            listInfo = skChannelArticleContentCnService.likeSelectAll(currentPage,pageSize,title,locale,categoryId,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    /**
     * 功能描述:插入
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     */
    @PostMapping
    public SkChannelArticleContentCn insertSelectiveInfo(@RequestBody SkChannelArticleContentCn record,@RequestParam String locale) {
        record.setAddTime(new Date());
        skChannelArticleContentCnService.insertSelective(record,locale);
        return record;

    }
    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleContentCn> lis, String locale){
        System.out.println("------"+locale+lis+"-----");
        int i = skChannelArticleContentCnService.batchUp(lis, locale);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);
    }

    /**
     * 功能描述:根据主键删除
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */

   /* @DeleteMapping
    public String deleteByPrimaryKeyInfo(@RequestParam Integer id) {
        int i = skChannelArticleContentCnService.deleteByPrimaryKey(id);
        if (i > 0) {
            return "删除成功";
        } else
            return "删除失败";
    }*/
    /**
     * 功能描述:根据ID查找
     *
     * @return com.niit.service.cms.pojo.SkChannelArticleContentCn
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
/*

    @GetMapping("/{id}")
    public SkChannelArticleContentCn selectByPrimaryKeyInfo(@PathVariable("id") Integer id) {
        SkChannelArticleContentCn contentCn = skChannelArticleContentCnService.selectByPrimaryKey(id);
        return contentCn;
    }
*/
    /**
     * 功能描述:查找所有
     *
     * @return java.util.List<com.niit.service.cms.pojo.SkChannelArticleContentCn>
     * @author huangwei
     * @date 2018/11/1
     * @params []
     */

    @GetMapping
    public PageInfo<SkChannelArticleContentCn> selectAllInfo(String key,int currentPage, int pageSize,@RequestParam String locale) {
        PageInfo<SkChannelArticleContentCn> listInfo = null;
        try {
            listInfo=skChannelArticleContentCnService.FuzzySearchBy(key,currentPage,pageSize,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }

    /**
     * 功能描述:批量删除
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [request]
     */
    @DeleteMapping
    public String deleteAd(HttpServletRequest request,@RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleContentCnService.deleteAd(id,locale);
        return id;
    }
    /**
     * 功能描述:批量审核
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     * @return java.lang.String
     */
  @PutMapping("/review")
    public  String updateSt(HttpServletRequest request,@RequestParam String locale){
      String id = request.getParameter("id");
        skChannelArticleContentCnService.updateSt(id,locale);
        return "批量审核";
    }
    @PutMapping("/top")
    public  String updateTo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateTo(id,locale);
        return "批量置顶";
    }
    @PutMapping("/red")
    public  String updateRe(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateRe(id,locale);
        return "批量推荐";
    }
    @PutMapping("/hot")
    public  String updateHo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateHo(id,locale);
        return "批量热门";
    }
    /**
     * 功能描述:更新修改
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     */

    @PutMapping
    public SkChannelArticleContentCn updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleContentCn record,@RequestParam String locale) {
        record.setUpdateTime(new Date());
        skChannelArticleContentCnService.updateByPrimaryKeySelective(record,locale);
        return record;

    }

//    //模糊查询
//    @PostMapping("/titles")
//    public PageInfo<SkChannelArticleContentCn> likeSelectAllInfo(int currentPage, int pageSize,String title,@RequestParam String locale) {
//        PageInfo<SkChannelArticleContentCn> listInfo=null;
//        try {
//            listInfo = skChannelArticleContentCnService.likeSelectAll(currentPage,pageSize,title,locale);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listInfo;
//    }



}

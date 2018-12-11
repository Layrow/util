package com.niit.service.cms.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCn;
import com.niit.service.cms.service.SkChannelArticleGoodsCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能描述:商城货物
 *
 * @author huangwei
 * @date 2018/11/1
 */
@RestController
@RequestMapping("/goods")
public class SkChannelArticleGoodsCnController {

    @Autowired
    private SkChannelArticleGoodsCnService skChannelArticleGoodsCnService;


    @GetMapping(value = "/main_category_fuzz_search")
    public String fuzzSearchAndMainCategory(String keyword,Integer channelId,String locale) {
        Map<String,Object> result=new LinkedHashMap<>();
        try {
            result=skChannelArticleGoodsCnService.easyLikeSelectAll(locale,keyword,channelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(result);
    }

    /**
     * @param channelId, locale
     * @Description TODO
     * @author yuwentao
     */
    @GetMapping(value = "/main_category_article_count")
    public String  getMainCategoryAndArticleCount(Integer channelId, String locale,@RequestParam(value = "keyword",required = false,defaultValue = "") String keyword) {
        Map<String,Object> map = null;
        try {
            map = skChannelArticleGoodsCnService.getArticleCountByCategory(channelId, locale,keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
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
    public SkChannelArticleGoodsCn insertSelectiveInfo(@RequestBody SkChannelArticleGoodsCn record,@RequestParam String locale) {
        record.setAddTime(new Date());
        skChannelArticleGoodsCnService.insertSelective(record,locale);
       return  record;

    }
    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleGoodsCn> lis, String locale){
        System.out.println("------"+locale+lis+"-----");
        int i = skChannelArticleGoodsCnService.batchUp(lis, locale);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);
    }

    /**
     * 功能描述: 根据主键进行删除
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
  /*  @DeleteMapping
    public String deleteByPrimaryKeyInfo(Integer id) {

        int i = skChannelArticleGoodsCnService.deleteByPrimaryKey(id);
        if (i > 0) {
            return "删除成功";
        } else
            return "删除失败";
    }*/

    /**
     * 功能描述:根据主键进行查找
     *
     * @return com.niit.service.cms.pojo.SkChannelArticleGoodsCn
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
  /*  @GetMapping
    public SkChannelArticleGoodsCn selectByPrimaryKeyInfo(Integer id) {
        SkChannelArticleGoodsCn skn = skChannelArticleGoodsCnService.selectByPrimaryKey(id);
        return skn;
    }*/

    /**
     * 功能描述:查找所有
     *
     * @return java.util.List<com.niit.service.cms.pojo.SkChannelArticleGoodsCn>
     * @author huangwei
     * @date 2018/11/1
     * @params []
     */
    @GetMapping
    public PageInfo<SkChannelArticleGoodsCn> selectAllInfo(@Param("categoryId") Integer categoryId, String key, int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkChannelArticleGoodsCn> listInfo = null;
        try {
            listInfo = skChannelArticleGoodsCnService.FuzzySearchBy(categoryId, key, currentPage, pageSize, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    /**
     * 功能描述:更新
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     */
    @PutMapping
    public SkChannelArticleGoodsCn  updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleGoodsCn record,@RequestParam String locale) {
        record.setUpdateTime(new Date());

        skChannelArticleGoodsCnService.updateByPrimaryKeySelective(record,locale);
        return  record;

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
        skChannelArticleGoodsCnService.deleteAd(id,locale);
        return  id;
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
        skChannelArticleGoodsCnService.updateSt(id,locale);
        return "批量审核";

    }
    @PutMapping("/top")
    public  String updateTo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateTo(id,locale);
        return "批量置顶";
    }
    @PutMapping("/red")
    public  String updateRe(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateRe(id,locale);
        return "批量推荐";
    }
    @PutMapping("/hot")
    public  String updateHo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateHo(id,locale);
        return "批量热门";
    }

    /**
     * 功能描述: 分页模糊查询
     *
     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleGoodsCn>
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     */
    @PostMapping("/titles")
    public PageInfo<Object> likeSelectAllInfo(Integer categoryId, String key, int currentPage, int pageSize, String title,String locale,Integer channelId,@RequestParam(value = "orderBy",required = false,defaultValue = "") String orderBy) {
        PageInfo<Object> listInfo = null;
        try {
            listInfo = skChannelArticleGoodsCnService.likeSelectAll(currentPage, pageSize, title, locale, categoryId, key,channelId,orderBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }


}

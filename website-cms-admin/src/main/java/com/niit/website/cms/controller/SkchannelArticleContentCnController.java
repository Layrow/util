package com.niit.website.cms.controller;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleContentCn;
import com.niit.website.cms.service.SkChannelArticleContentCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @Auther: huangwei
 * @Date: 2018/11/5 11:10
 * @Description:
 */
@RestController
@RequestMapping("/content")
public class SkchannelArticleContentCnController {
    @Autowired
    private SkChannelArticleContentCnService skChannelArticleContentCnService;

    /**
     * 功能描述:分页模糊查询
     *
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleContentCn>
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleContentCn> likeSelectAllInfo(Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleContentCn> listInfo = null;
        try {
            listInfo = skChannelArticleContentCnService.likeSelectAll(categoryId, key, currentPage, pageSize, title, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }

    /**
     * 功能描述:
     *
     * @return java.lang.Integer
     * @author huangwei
     * @date 2018/11/5
     * @params [record]
     */
    @PostMapping
    public Integer insertSkArticleCategoryCn(@RequestBody SkChannelArticleContentCn record, @RequestParam String locale) {
        Date d = new Date();
        Integer insertStatus = 0;
        try {
            record.setAddTime(d);
            record.setUpdateTime(d);
            insertStatus = skChannelArticleContentCnService.insertSelective(record, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;

    }

    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleContentCn> lis, @RequestParam String locale) {
        // System.out.println("------"+locale+lis+"-----");
        skChannelArticleContentCnService.batchUp(lis, locale);
        return "保存修改";
    }


    @GetMapping
    public PageInfo<SkChannelArticleContentCn> selectAllInfo(String key, int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkChannelArticleContentCn> listInfo = null;
        try {
            listInfo = skChannelArticleContentCnService.FuzzySearchBy(key, currentPage, pageSize, locale);
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
    public Integer deleteAd(HttpServletRequest request, @RequestParam String locale) {
        Integer deleteMoreStatus = 0;
        String id = request.getParameter("id");
        try {
            skChannelArticleContentCnService.deleteAd(id, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteMoreStatus;
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
    public Integer updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleContentCn record, @RequestParam String locale) {
        return skChannelArticleContentCnService.updateByPrimaryKeySelective(record, locale);
    }

    /*  *//**
     * 功能描述:  分页模糊查询
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleContentCn>
     *//*
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleContentCn> likeSelectAllInfo(int currentPage, int pageSize,String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleContentCn> listInfo=null;
        try {
            listInfo = skChannelArticleContentCnService.likeSelectAll(currentPage,pageSize,title,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }*/

    /**
     * 功能描述:批量审核
     *
     * @return java.lang.String
     * @author huangwei
     * @date :2018/11/12
     * @params [id]
     */
    @PutMapping("/review")
    public String updateSt(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateSt(id, locale);
        return "批量审核";
    }

    @PutMapping("/top")
    public String updateTo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateTo(id, locale);
        return "批量置顶";
    }

    @PutMapping("/red")
    public String updateRe(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateRe(id, locale);
        return "批量推荐";
    }

    @PutMapping("/hot")
    public String updateHo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleContentCnService.updateHo(id, locale);
        return "批量热门";
    }


}

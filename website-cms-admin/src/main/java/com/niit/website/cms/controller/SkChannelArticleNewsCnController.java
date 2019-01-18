package com.niit.website.cms.controller;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleNewsCn;
import com.niit.website.cms.service.SkChannelArticleNewsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: huangwei
 * @Date: 2018/11/5 11:23
 * @Description:
 */
@RestController
@RequestMapping(value = "/news")
public class SkChannelArticleNewsCnController {
    @Autowired
    private SkChannelArticleNewsCnService skChannelArticleNewsCnService;

    /**
     * 功能描述:分页模糊查询
     *
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleNewsCn>
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleNewsCn> likeSelectAllInfo(Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleNewsCn> listInfo = null;
        try {
            listInfo = skChannelArticleNewsCnService.likeSelectAll(categoryId, key, currentPage, pageSize, title, locale);
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
    public Integer insertSkArticleCategoryCn(@RequestBody SkChannelArticleNewsCn record, @RequestParam String locale) {
        Integer insertStatus = 0;
        try {
            insertStatus = skChannelArticleNewsCnService.insertSelective(record, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleNewsCn> lis, @RequestParam String locale) {
        // System.out.println("------"+locale+lis+"-----");
        skChannelArticleNewsCnService.batchUp(lis, locale);
        return "保存修改";
    }

    @GetMapping
    public PageInfo<SkChannelArticleNewsCn> selectAllInfo(String key, int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkChannelArticleNewsCn> listInfo = null;
        try {
            listInfo = skChannelArticleNewsCnService.FuzzySearchBy(key, currentPage, pageSize, locale);
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
            skChannelArticleNewsCnService.deleteAd(id, locale);
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
    public Integer updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleNewsCn record, @RequestParam String locale) {
        return skChannelArticleNewsCnService.updateByPrimaryKeySelective(record, locale);
    }

    /*  *//**
     * 功能描述:分页模糊查询
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleNewsCn>
     *//*
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleNewsCn> likeSelectAllInfo(int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleNewsCn> listInfo = null;
        try {
            listInfo = skChannelArticleNewsCnService.likeSelectAll(currentPage, pageSize, title,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
*/

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
        skChannelArticleNewsCnService.updateSt(id, locale);
        return "批量审核";
    }

    @PutMapping("/top")
    public String updateTo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleNewsCnService.updateTo(id, locale);
        return "批量置顶";
    }

    @PutMapping("/red")
    public String updateRe(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleNewsCnService.updateRe(id, locale);
        return "批量推荐";
    }

    @PutMapping("/hot")
    public String updateHo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleNewsCnService.updateHo(id, locale);
        return "批量热门";
    }


}

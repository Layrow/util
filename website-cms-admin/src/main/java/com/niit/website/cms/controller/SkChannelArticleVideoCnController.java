package com.niit.website.cms.controller;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkChannelArticleVideoCn;
import com.niit.website.cms.service.SkChannelArticleVideoCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Auther: huangwei
 * @Date: 2018/11/5 11:23
 * @Description:
 */
@RestController
@RequestMapping("/video")
public class SkChannelArticleVideoCnController {
    @Autowired
    private SkChannelArticleVideoCnService skChannelArticleVideoCnService;

    /**
     * 功能描述:分页模糊查询
     *
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleGoodsCn>
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleVideoCn> likeSelectAllInfo(Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleVideoCn> listInfo = null;
        try {
            listInfo = skChannelArticleVideoCnService.likeSelectAll(categoryId, key, currentPage, pageSize, title, locale);
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
    public Integer insertSkArticleCategoryCn(@RequestBody SkChannelArticleVideoCn record, @RequestParam String locale) {
        Date d = new Date();
        Integer insertStatus = 0;
        try {
            record.setAddTime(d);
            record.setUpdateTime(d);
            insertStatus = skChannelArticleVideoCnService.insertSelective(record, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;

    }

    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleVideoCn> lis, @RequestParam String locale) {
        // System.out.println("------"+locale+lis+"-----");
        skChannelArticleVideoCnService.batchUp(lis, locale);
        return "保存修改";
    }

    @GetMapping
    public PageInfo<SkChannelArticleVideoCn> selectAllInfo(String key, int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkChannelArticleVideoCn> listInfo = null;
        try {
            listInfo = skChannelArticleVideoCnService.FuzzySearchBy(key, currentPage, pageSize, locale);
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
            skChannelArticleVideoCnService.deleteAd(id, locale);
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
    public Integer updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleVideoCn record, @RequestParam String locale) {
        return skChannelArticleVideoCnService.updateByPrimaryKeySelective(record, locale);
    }

    /**
     * 功能描述: 批量审核
     *
     * @return java.lang.String
     * @author huangwei
     * @date :2018/11/12
     * @params [id]
     */
    @PutMapping("/review")
    public String updateSt(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateSt(id, locale);
        return "批量审核";
    }

    @PutMapping("/top")
    public String updateTo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateTo(id, locale);
        return "批量置顶";
    }

    @PutMapping("/red")
    public String updateRe(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateRe(id, locale);
        return "批量推荐";
    }

    @PutMapping("/hot")
    public String updateHo(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleVideoCnService.updateHo(id, locale);
        return "批量热门";
    }


}

package com.niit.service.cms.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import com.niit.service.cms.service.SkChannelArticleNewsCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 功能描述:新闻内容
 *
 * @author huangwei
 * @date 2018/11/1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/news")
public class SkChannelArticleNewsCnController {

    @Autowired
    private SkChannelArticleNewsCnService skChannelArticleNewsCnService;

    /**
     * 功能描述: 分页模糊查询
     *
     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleNewsCn>
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleNewsCn> likeSelectAllInfo(@Param("categoryId") Integer categoryId, String key, int currentPage, int pageSize, String title, @RequestParam String locale) {
        PageInfo<SkChannelArticleNewsCn> listInfo = null;
        try {
            listInfo = skChannelArticleNewsCnService.likeSelectAll(currentPage, pageSize, title, locale, categoryId, key);
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
    public SkChannelArticleNewsCn insertSelectiveInfo(@RequestBody SkChannelArticleNewsCn record, @RequestParam String locale) {
        Date d = new Date();
        record.setAddTime(d);
        record.setUpdateTime(d);
        skChannelArticleNewsCnService.insertSelective(record, locale);
        return record;
    }

    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleNewsCn> lis, String locale) {
        System.out.println("------" + locale + lis + "-----");
        int i = skChannelArticleNewsCnService.batchUp(lis, locale);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);
    }

    /**
     * 功能描述:根据ID删除
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
/*

    @DeleteMapping("/delete")
    public String deleteByPrimaryKeyInfo(Integer id) {
        int i = skChannelArticleNewsCnService.deleteByPrimaryKey(id);
        if (i > 0) {
            return "删除成功";
        } else
            return "删除失败";
    }
*/

    /**
     * 功能描述:根据ID查找
     *
     * @return com.niit.service.cms.pojo.SkChannelArticleNewsCn
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
    @GetMapping("/id")
    public SkChannelArticleNewsCn selectByPrimaryKeyInfo(Integer id, @RequestParam String locale) {
        SkChannelArticleNewsCn Cn = skChannelArticleNewsCnService.selectByPrimaryKey(id, locale);
        return Cn;
    }

    /**
     * 功能描述:查找所有
     *
     * @return java.util.List<com.niit.service.cms.pojo.SkChannelArticleNewsCn>
     * @author huangwei
     * @date 2018/11/1
     * @params []
     */
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
     * 功能描述:更新修改
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     */
    @PutMapping
    public SkChannelArticleNewsCn updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleNewsCn record, @RequestParam String locale) {
        record.setUpdateTime(new Date());
        skChannelArticleNewsCnService.updateByPrimaryKeySelective(record, locale);
        return record;
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
    public String deleteAd(HttpServletRequest request, @RequestParam String locale) {
        String id = request.getParameter("id");
        skChannelArticleNewsCnService.deleteAd(id, locale);
        return id;
    }

    /**
     * 功能描述:批量审核
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [request]
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

//    /**
//     * 功能描述:分页模糊查询
//     * @author huangwei
//     * @date 2018/11/8
//     * @params [currentPage, pageSize, title]
//     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleNewsCn>
//     */
//    @PostMapping("/titles")
//    public PageInfo<SkChannelArticleNewsCn> likeSelectAllInfo(int currentPage, int pageSize, String title,@RequestParam String locale) {
//        PageInfo<SkChannelArticleNewsCn> listInfo=null;
//        try {
//            listInfo = skChannelArticleNewsCnService.likeSelectAll(currentPage,pageSize,title,locale);
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
            info = skChannelArticleNewsCnService.selectNewsByCategoryId(locale, categoryId, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }


}
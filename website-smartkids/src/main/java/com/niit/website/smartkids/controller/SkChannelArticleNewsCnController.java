package com.niit.website.smartkids.controller;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleNewsCn;
import com.niit.website.smartkids.service.SkChannelArticleNewsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 功能描述:新闻内容
 *
 * @author lyh
 * @date 2018/11/1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/news")
public class SkChannelArticleNewsCnController {

    @Autowired
    private SkChannelArticleNewsCnService skChannelArticleNewsCnService;


    // 按照栏目类别ID并且status = 1查找新闻
    @GetMapping("/{locale}/{categoryId}")
    public String selectNewsByCategoryId(@PathVariable("locale") String locale, @PathVariable("categoryId") Integer categoryId,
                                         Integer currentPage, Integer pageSize) {
        String newsInfos = null;
        try {
            newsInfos = skChannelArticleNewsCnService.selectNewsByCategoryId(locale, categoryId, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsInfos;
    }

    // 查询channel特定的，parent_id为0的栏目类别
    @GetMapping("/articleCategory")
    public List<SkArticleCategoryCn> selectCategory(@RequestParam String locale, @RequestParam Integer channelId) {
        return skChannelArticleNewsCnService.selectCategory(locale, channelId);
    }

    // 根据ID查找
    @GetMapping("/id")
    public SkChannelArticleNewsCn selectByPrimaryKeyInfo(Integer id, @RequestParam String locale) {
        SkChannelArticleNewsCn Cn = skChannelArticleNewsCnService.selectByPrimaryKeyInfo(id, locale);
        return Cn;
    }

}
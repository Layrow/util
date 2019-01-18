package com.niit.website.smartkids.controller;

import com.niit.website.smartkids.pojo.SkArticleCategoryCn;
import com.niit.website.smartkids.pojo.SkChannelArticleContentCn;
import com.niit.website.smartkids.service.SkChannelArticleContentCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述:关于我们内容
 *
 * @author huangwei
 * @date 2018/11/1
 */
@CrossOrigin
@RestController
@RequestMapping("/content")
public class SkchannelArticleContentCnController {

    @Autowired
    private SkChannelArticleContentCnService skChannelArticleContentCnService;


    // 按照栏目类别ID并且status = 1查找新闻
    @GetMapping("/{locale}/{categoryId}")
    public String selectNewsByCategoryId(@PathVariable("locale") String locale, @PathVariable("categoryId") Integer categoryId,
                                         Integer currentPage, Integer pageSize) {
        String newsInfos = null;
        try {
            newsInfos = skChannelArticleContentCnService.selectContentByCategoryId(locale, categoryId, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsInfos;
    }

    // 查询channel特定的，parent_id为0的栏目类别
    @GetMapping("/articleCategory")
    public List<SkArticleCategoryCn> selectCategory(@RequestParam String locale, @RequestParam Integer channelId) {
        return skChannelArticleContentCnService.selectCategory(locale, channelId);
    }

    // 根据ID查找
    @GetMapping("/id")
    public SkChannelArticleContentCn selectByPrimaryKeyInfo(Integer id, @RequestParam String locale) {
        SkChannelArticleContentCn Cn = skChannelArticleContentCnService.selectByPrimaryKeyInfo(id, locale);
        return Cn;
    }

}

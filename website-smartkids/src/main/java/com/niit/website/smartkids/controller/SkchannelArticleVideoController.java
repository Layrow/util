package com.niit.website.smartkids.controller;

import com.niit.website.smartkids.pojo.SkChannelArticleVideoCn;
import com.niit.website.smartkids.service.SkchannelArticleVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.23 09:52
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.23 09:52
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/video")
public class SkchannelArticleVideoController {
    @Autowired
    SkchannelArticleVideoService skchannelArticleVideoService;

    @GetMapping(value = "/category")
    public Integer getByCategory(String key, Integer pageSize, Integer currentPage, Integer categoryId) {
//        skchannelArticleVideoService.
        return 1;
    }

    // 按照栏目类别ID并且status = 1查找新闻
    @GetMapping("/{locale}/{categoryId}")
    public String selectNewsByCategoryId(@PathVariable("locale") String locale, @PathVariable("categoryId") Integer categoryId,
                                         Integer currentPage, Integer pageSize) {
        String newsInfos = null;
        try {
            newsInfos = skchannelArticleVideoService.selectVideoByCategoryId(locale, categoryId, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsInfos;
    }

    // 查询channel特定的，parent_id为0的栏目类别
    @GetMapping("/articleCategory")
    public List<SkChannelArticleVideoCn> selectCategory(@RequestParam String locale, @RequestParam Integer channelId) {
        return skchannelArticleVideoService.selectCategory(locale, channelId);
    }

    // 根据ID查找
    @GetMapping("/id")
    public SkChannelArticleVideoCn selectByPrimaryKeyInfo(@RequestParam Integer id, @RequestParam String locale) {
        SkChannelArticleVideoCn Cn = skchannelArticleVideoService.selectByPrimaryKeyInfo(id, locale);
        return Cn;
    }


}

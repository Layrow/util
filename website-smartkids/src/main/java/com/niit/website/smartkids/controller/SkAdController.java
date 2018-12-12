package com.niit.website.smartkids.controller;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkAd;
import com.niit.website.smartkids.pojo.SkAdContent;
import com.niit.website.smartkids.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SkAdController
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 16:15
 **/
@CrossOrigin
@RestController
@RequestMapping("/ad")
public class SkAdController {

    @Autowired
    private SkAdService skAdService;

    // 查询特定广告位下的所有广告 order排序 可以查询状态不同的广告位
    @GetMapping("/content")
    public PageInfo<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId, @RequestParam(defaultValue = "1") Integer status,
                                              @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<SkAdContent> pageInfo = null;
        try {
            pageInfo = skAdService.selectByAdId(adId,status,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    // 查询所有广告位,默认查询所有的广告位，可以添加参数用来查询不同状态的广告位
    @GetMapping
    public PageInfo<SkAd> selectAllAdsense(@RequestParam(defaultValue = "1") Integer status, @RequestParam("currentPage") Integer currentPage,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<SkAd> pageInfo = null;
        try {
            pageInfo = skAdService.selectAllAdsense(status,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}

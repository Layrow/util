package com.niit.website.smartkids.controller;

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
    public List<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId, @RequestParam(defaultValue = "1") Integer status) {
        List<SkAdContent> skAdContentList = null;
        try {
            skAdContentList = skAdService.selectByAdId(adId,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skAdContentList;
    }

    // 查询所有广告位,默认查询所有的广告位，可以添加参数用来查询不同状态的广告位
    @GetMapping
    public List<SkAd> selectAllAdsense(@RequestParam(defaultValue = "1") Integer status) {
        List<SkAd> selectAllAdsenseList = null;
        try {
            selectAllAdsenseList = skAdService.selectAllAdsense(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectAllAdsenseList;
    }
}

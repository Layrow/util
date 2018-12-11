package com.niit.website.cms.controller;

import com.niit.website.cms.pojo.SkAd;
import com.niit.website.cms.pojo.SkAdContent;
import com.niit.website.cms.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName SkAdController
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 9:44
 **/
@CrossOrigin
@RestController
@RequestMapping("/ad")
public class SkAdController {

    @Autowired
    private SkAdService skAdService;

    // 新增广告位
    @PostMapping
    public void insertSelective(@RequestBody SkAd record) {
        try {
            skAdService.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询所有广告位,默认查询所有的广告位，可以添加参数用来查询不同状态的广告位
    @GetMapping
    public List<SkAd> selectAllAdsense(@RequestParam(defaultValue = " ") Integer status) {
        List<SkAd> selectAllAdsenseList = null;
        try {
            selectAllAdsenseList = skAdService.selectAllAdsense(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectAllAdsenseList;
    }

    // 批量删除广告位
    @DeleteMapping
    public void deleteAdByPrimaryKey(HttpServletRequest request) {
        String ids = request.getParameter("id");
        try {
            skAdService.deleteMoreAd(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询特定广告位下的所有广告 order排序 可以查询状态不同的广告位
    @GetMapping("/content")
    public List<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId, @RequestParam(defaultValue = "") Integer status) {
        List<SkAdContent> skAdContentList = null;
        try {
            skAdContentList = skAdService.selectByAdId(adId,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skAdContentList;
    }

    // 批量删除广告
    @DeleteMapping("/content")
    public void deleteAdContentByPrimaryKey(HttpServletRequest request) {
        String ids = request.getParameter("id");
        try {
            skAdService.deleteMoreAdContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除某个广告位下的所有广告
    @DeleteMapping("/contents")
    public void deleteAllByAdId(@RequestParam Integer adId) {
        try {
            skAdService.deleteByAdId(adId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 新增特定广告位下的广告
    @PostMapping("/content")
    public void insertAdContentSelective(@RequestBody SkAdContent skAdContent) {
        try {
            skAdService.insertAdContentSelective(skAdContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 批量审核广告
    @PutMapping
    public void updateAdContent(HttpServletRequest request) {
        String ids = request.getParameter("id");
        try {
            skAdService.updateMoreAdContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

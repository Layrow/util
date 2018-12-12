package com.niit.service.cms.controller;

import com.niit.service.cms.pojo.SkAd;
import com.niit.service.cms.pojo.SkAdContent;
import com.niit.service.cms.service.SkAdService;
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

    // 添加广告位
    @PostMapping
    public Integer insertSelective(@RequestBody SkAd record) {
        Integer insertStatus = 0;
        try {
            insertStatus = skAdService.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    // 查询所有广告位,默认查询所有的广告位，可以添加参数用来查询不同状态的广告位
    @GetMapping
    public List<SkAd> selectAllAdsense(@RequestParam(value = "status",required=false) Integer status) {
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
    public Integer deleteAdByPrimaryKey(HttpServletRequest request) {
        String ids = request.getParameter("id");
        Integer deleteStatus = 0;
        try {
            deleteStatus = skAdService.deleteMoreAd(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    // 查询特定广告位下的所有广告 order排序 可以查询状态不同的广告位
    @GetMapping("/content")
    public List<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId,@RequestParam(value = "status",required=false) Integer status) {
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
    public Integer deleteAdContentByPrimaryKey(HttpServletRequest request) {
        String ids = request.getParameter("id");
        Integer deleteStatus = 0;
        try {
            deleteStatus = skAdService.deleteMoreAdContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    // 删除某个广告位下的所有广告
    @DeleteMapping("/contents")
    public Integer deleteAllByAdId(@RequestParam Integer adId) {
        Integer deleteStatus  = 0;
        try {
            deleteStatus = skAdService.deleteByAdId(adId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    // 新增特定广告位下的广告
    @PostMapping("/content")
    public Integer insertAdContentSelective(@RequestBody SkAdContent skAdContent) {
        Integer insertStatus = 0;
        try {
            insertStatus = skAdService.insertAdContentSelective(skAdContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    // 批量审核广告
    @PutMapping
    public Integer updateAdContent(HttpServletRequest request) {
        String ids = request.getParameter("id");
        Integer updateStatus = 0;
        try {
            updateStatus = skAdService.updateMoreAdContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

}

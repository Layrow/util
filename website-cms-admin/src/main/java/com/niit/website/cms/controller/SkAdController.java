package com.niit.website.cms.controller;

import com.github.pagehelper.PageInfo;
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
    public PageInfo<SkAd> selectAllAdsense(@RequestParam(defaultValue = "") Integer status,@RequestParam("currentPage") Integer currentPage,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<SkAd> pageInfo = null;
        try {
            pageInfo = skAdService.selectAllAdsense(status,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
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
    public PageInfo<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId, @RequestParam(defaultValue = "") Integer status,
                                          @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<SkAdContent> pageInfo = null;
        try {
            pageInfo = skAdService.selectByAdId(adId,status,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
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

    // 修改广告内容
    @PutMapping("/content")
    public void updateAdCotent(@RequestBody SkAdContent skAdContent) {
        try {
            skAdService.updateAdContentByPrimaryKeySelective(skAdContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 修改广告位内容
    @PutMapping("/advert")
    public void updateAd(@RequestBody SkAd skAd) {
        try {
            skAdService.updateAd(skAd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 根据Title进行模糊查询
    @PostMapping("/title")
    public PageInfo<SkAd> likeSelectAdAllByTitle(@RequestParam("title") String title,
                                                 @RequestParam("currentPage") Integer currentPage,
                                                 @RequestParam("pageSize") Integer pageSize) {
        PageInfo<SkAd> pageInfo = null;
        try {
            pageInfo = skAdService.likeSelectAdAllByTitle(title, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    // 批量修改排序值
    @PutMapping("/contents")
    public void updateMoreAdOrder(@RequestBody List<SkAdContent> skAdContentList) {
        try {
            skAdService.updateAdContentMoreSortId(skAdContentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询所有广告
    @GetMapping("/contents")
    public String selectAllAd(@RequestParam(value = "title",defaultValue = "") String title,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {
        return skAdService.selectAllAd(title,currentPage,pageSize);
    }

    // 查询所有广告位
    @GetMapping("/all")
    public List<SkAd> selectAll() {
        List<SkAd> skAdList = null;
        try {
            skAdList = skAdService.selectAllAdsenses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skAdList;
    }
}

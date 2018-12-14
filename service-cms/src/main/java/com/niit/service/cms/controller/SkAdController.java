package com.niit.service.cms.controller;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkAd;
import com.niit.service.cms.pojo.SkAdContent;
import com.niit.service.cms.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SkAdController
 * @Description 广告位和广告管理
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
        record.setCreateTime(new Date());
        record.setStatus(1);
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
    public PageInfo<SkAd> selectAllAdsense(@RequestParam(value = "status",required=false) Integer status,@RequestParam("currentPage") Integer currentPage,
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
    public PageInfo<SkAdContent> selectByAdId(@RequestParam("adId")Integer adId,@RequestParam(value = "status",required=false) Integer status,
                                              @RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") Integer pageSize) {
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
        skAdContent.setAdStatus(0);
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
    public Integer updateAdMoreContent(HttpServletRequest request) {
        String ids = request.getParameter("id");
        Integer updateStatus = 0;
        try {
            updateStatus = skAdService.updateMoreAdContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    // 修改广告内容
    @PutMapping("/content")
    public Integer updateAdCotent(@RequestBody SkAdContent skAdContent) {
        Integer updateAdContentStatus = 0;
        try {
            updateAdContentStatus = skAdService.updateAdContentByPrimaryKeySelective(skAdContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateAdContentStatus;
    }

    // 修改广告位内容
    @PutMapping("/advert")
    public Integer updateAd(@RequestBody SkAd skAd) {
        Integer updateAdStatus = 0;
        try {
            updateAdStatus = skAdService.updateAd(skAd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateAdStatus;
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
    public Integer updateMoreAdOrder(@RequestBody List<SkAdContent> skAdContentList) {
        Integer updateMoreStatus = 0;
        try {
            if (skAdContentList != null && !skAdContentList.isEmpty()) {
                updateMoreStatus = skAdService.updateAdContentMoreSortId(skAdContentList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateMoreStatus;
    }

    // 查询所有广告
    @GetMapping("/contents")
    public String selectAllAd(@RequestParam(value = "status",required=false) Integer status,@RequestParam(value = "title",defaultValue = "") String title,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize) {
        return skAdService.selectAllAd(status,title,currentPage,pageSize);
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

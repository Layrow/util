package com.niit.service.cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.service.SkArticleCategoryCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SkArticleCategoryCnController
 * @Description ArticleCategoryCn
 * @Author liyuhao
 * @Date 2018/10/30 15:26
 **/
@CrossOrigin
@RestController
@RequestMapping("/skArticleCategoryCn")
public class SkArticleCategoryCnController {

    @Autowired
    private SkArticleCategoryCnService skArticleCategoryCnService;

    @PostMapping("/parent_list")
    public String getParentList(@RequestBody List<Integer> list, Integer channelId, String locale) {
        Map<Integer,List<SkArticleCategoryCn>> map=new LinkedHashMap<>();
        try {
            map= skArticleCategoryCnService.getParentList(list,channelId,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    /**
     * @Description 插入频道下的栏目 父级或子级栏目
     * @Date 2018/10/30 15:39
     * @Param [record]
     * @Return int
     */
    @PostMapping("/articleCategory/{locale}")
    public Integer insertSkArticleCategoryCn(@RequestBody SkArticleCategoryCn record,@PathVariable("locale") String locale) {
        Integer insertStatus = 0;
        try {
            insertStatus = skArticleCategoryCnService.insert(record,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * @Description 查询所有目录分类(包含树状结构)
     * @Date 2018/10/31 13:49
     * @Param []
     * @Return java.util.List<com.niit.service.cms.pojo.SkArticleCategoryCn>
     **/
    @GetMapping("/articleCategory/{locale}/{channelId}")
    public List<SkArticleCategoryCn> getNodeTree(@PathVariable Integer channelId,@PathVariable("locale") String locale) {
        List<SkArticleCategoryCn> skArticleCategoryCnList = null;
        try {
            skArticleCategoryCnList = skArticleCategoryCnService.getNodeTree(channelId,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skArticleCategoryCnList;
    }

    /**
     * @Description 修改栏目类别信息
     * @Date 2018/11/3 0:07
     * @Param [record]
     * @Return java.lang.Integer
     **/
    @PutMapping("/articleCategory/{locale}")
    public Integer updateSkArticleCategoryCn(@RequestBody SkArticleCategoryCn record,@PathVariable("locale") String locale) {
        return skArticleCategoryCnService.updateByPrimaryKeySelective(record,locale);
    }

    /**
     * @Description 根据指定ID删除栏目类别及其子孙栏目
     * @Date 2018/11/12 9:38
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @DeleteMapping("/articleCategory/{locale}/{id}")
    public Integer getParentIdTree(@PathVariable String id,@PathVariable("locale") String locale) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skArticleCategoryCnService.deleteAllCategoryCn(id,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    /**
     * @Description 根据父类id查询对应的子类
     * @Date 2018/11/30 9:32
     * @Param [parentId]
     * @Return java.util.List<com.niit.service.cms.pojo.SkArticleCategoryCn>
     **/
    @GetMapping("/articleCategory/next/{parentId}")
    public List<SkArticleCategoryCn> getNextInfos(@PathVariable("parentId") Integer parentId) {
        return skArticleCategoryCnService.getNextInfos(parentId);
    }

    /**
     * @Description 更新多个排序id
     * @Date 2018/11/29 16:24
     * @Param [skArticleCategoryCnList, locale]
     * @Return void
     **/
    @PutMapping("/articleCategory/moreArticleCategory/{locale}")
    public void updateMoreSortId(@RequestBody List<SkArticleCategoryCn> skArticleCategoryCnList,@PathVariable String locale) {
        if (skArticleCategoryCnList != null && !skArticleCategoryCnList.isEmpty()) {
            skArticleCategoryCnService.updateMoreSortId(skArticleCategoryCnList,locale);
        }
        return;
    }

    /**
     * @Description 查询channel特定的，parent_id为0的栏目类别
     * @Date 2018/11/29 9:26
     * @Param [locale]
     * @Return java.util.List<com.niit.service.cms.pojo.SkArticleCategoryCn>
     **/
    @GetMapping("/articleCategory")
    public List<SkArticleCategoryCn> selectCategory(@RequestParam String locale,@RequestParam Integer channelId) {
        return  skArticleCategoryCnService.selectNewsCategory(locale,channelId);
    }

}


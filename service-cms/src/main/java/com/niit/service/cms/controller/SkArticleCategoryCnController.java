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
 * @Description ArticleCategoryCn 排序保存？
 * 查询栏目分类的时候，前端加载信息的时候按照sortId排序。更改sortId的时候，调用更新api进行更新，
 * 然后按照sortId重新排序，加载页面
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
     * @Description  根据ChannelId获取特定栏目类别
     * @Date 2018/10/31 11:18
     * @Param []
     * @Return java.util.List<com.niit.service.cms.pojo.SkArticleCategoryCn>
     **/
//    @GetMapping("/articleCategory/{channelId}")
//    public List<SkArticleCategoryCn> getAllCategoryByChannelId(@PathVariable Integer channelId) {
//        List<SkArticleCategoryCn> skArticleCategoryCnList = null;
//        try {
//            skArticleCategoryCnList = skArticleCategoryCnService.selectArticleCategoryByChannelId(channelId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return skArticleCategoryCnList;
//    }


    /**
     * @Description 查询所有目录分类(包含层次结构) 查询速度太慢// TODO 改善查询速度？？？
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

/*    // 查询所有
    @GetMapping("/articleCategory")
    public List<SkArticleCategoryCn> getAllSkArticleCategoryCn() {
        return skArticleCategoryCnService.selectAllCategory();
    }*/

    /**
     * @Description 更新多个排序id
     * @Date 2018/11/29 16:24
     * @Param [skArticleCategoryCnList, locale]
     * @Return void
     **/
    @PutMapping("/articleCategory/moreArticleCategory/{locale}")
    public void updateMoreSortId(@RequestBody List<SkArticleCategoryCn> skArticleCategoryCnList,@PathVariable String locale) {
        skArticleCategoryCnService.updateMoreSortId(skArticleCategoryCnList,locale);
    }

    // 查询channel为1的，parent_id为0的栏目类别
    @GetMapping("/articleCategory")
    public List<SkArticleCategoryCn> selectNewsCategory(@RequestParam String locale) {
        return  skArticleCategoryCnService.selectNewsCategory(locale);
    }
}


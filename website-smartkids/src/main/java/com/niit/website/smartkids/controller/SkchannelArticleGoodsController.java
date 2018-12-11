package com.niit.website.smartkids.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsCn;
import com.niit.website.smartkids.service.SkchannelArticleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

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
@RequestMapping("/smartkids/mall")
public class SkchannelArticleGoodsController {
    @Autowired
    SkchannelArticleGoodsService skchannelArticleGoodsService;

    @GetMapping(value = "/main_category_fuzz_search")
    public Map<String,Object> fuzzSearchAndMainCategory(String keyword,Integer channelId,String locale) {
        Map<String,Object> result=new LinkedHashMap<>();
        try {
            Long time1=System.currentTimeMillis();
            String mapString =skchannelArticleGoodsService.easyLikeSelectAll(keyword,channelId,locale);
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (mapString == null || mapString.isEmpty()) {
                return result;
            }
            result = gson.fromJson(mapString, type);
            Long time2=System.currentTimeMillis();
            System.out.println("---------------------------------------"+(time2-time1));
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    /**
     * @param channelId, locale
     * @Description TODO
     * @author yuwentao
     */
    @GetMapping(value = "/main_category_article_count")
    public Map<String,Object> getMainCategoryAndArticleCount(Integer channelId, String locale,@RequestParam(value = "keyWord",required = false,defaultValue = "") String keyWord) {
        Map<String,Object> map = null;

        try {
            String mapString = skchannelArticleGoodsService.getArticleCountByCategory(channelId, locale,keyWord);
            //把String转成Map
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (mapString == null || mapString.isEmpty()) {
                return map;
            }
            map = gson.fromJson(mapString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @PostMapping(value = "/category")
    public PageInfo<SkChannelArticleGoodsCn> getByCategory(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale, Integer channelId,@RequestParam(value = "orderBy",required = false,defaultValue = "click") String orderBy){
        PageInfo<SkChannelArticleGoodsCn> pageInfoList=null;
        try {
            pageInfoList=skchannelArticleGoodsService.getByCategory(categoryId,key,currentPage,pageSize,title,locale,channelId,orderBy);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageInfoList;
    }





}

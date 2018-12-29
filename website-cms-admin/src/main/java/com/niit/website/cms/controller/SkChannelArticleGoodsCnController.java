package com.niit.website.cms.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.website.cms.pojo.SkChannelArticleGoodsCn;
import com.niit.website.cms.service.SkChannelArticleGoodsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @Auther: huangwei
 * @Date: 2018/11/5 11:22
 * @Description:
 */
@RestController
@RequestMapping("/goods")
public class SkChannelArticleGoodsCnController {
    @Autowired
    private SkChannelArticleGoodsCnService skChannelArticleGoodsCnService;
    /**
     * 功能描述:
     * @author huangwei
     * @date 2018/11/5
     * @params [record]
     * @return java.lang.Integer
     */
    @PostMapping
    public Integer insertSkArticleCategoryCn(@RequestBody SkChannelArticleGoodsCn record, @RequestParam String locale) {
        Date d = new Date();
        Integer insertStatus = 0;
        try {
            record.setAddTime(d);
            record.setUpdateTime(d);
            insertStatus = skChannelArticleGoodsCnService.insertSelective(record,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }
    @PutMapping("/batch")
    public String batchUp(@RequestBody List<SkChannelArticleGoodsCn> lis, @RequestParam String locale){
        // System.out.println("------"+locale+lis+"-----");
        skChannelArticleGoodsCnService.batchUp(lis,locale);
        return "保存修改";
    }

    @GetMapping
    public PageInfo<SkChannelArticleGoodsCn> selectAllInfo(String key,int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkChannelArticleGoodsCn> listInfo = null;
        try {
            listInfo = skChannelArticleGoodsCnService.FuzzySearchBy(key,currentPage,pageSize,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }

//    @GetMapping
//    public PageInfo<SkChannelArticleGoodsCn> selectAllInfo(int currentPage, int pageSize) {
//        PageInfo<SkChannelArticleGoodsCn> listInfo=null;
//        try {
//            listInfo = skChannelArticleGoodsCnService.selectAll(currentPage,pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listInfo;
//    }
    /**
     * 功能描述:批量删除
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [request]
     */
    @DeleteMapping
    public Integer deleteAd(HttpServletRequest request, @RequestParam String locale) {
        Integer deleteMoreStatus = 0;
        String id = request.getParameter("id");
        try {
            skChannelArticleGoodsCnService.deleteAd(id,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteMoreStatus;
    }
    /**
     * 功能描述:更新修改
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/1
     * @params [record]
     */

    @PutMapping
    public Integer updateByPrimaryKeySelectiveInfo(@RequestBody SkChannelArticleGoodsCn record, @RequestParam String locale) {
        return skChannelArticleGoodsCnService.updateByPrimaryKeySelective(record,locale);
    }

    /**
     * 功能描述:分页模糊查询
     * @author huangwei
     * @date 2018/11/8
     * @params [currentPage, pageSize, title]
     * @return com.github.pagehelper.PageInfo<com.niit.website.cms.pojo.SkChannelArticleGoodsCn>
     */
    @PostMapping("/titles")
    public PageInfo<SkChannelArticleGoodsCn> likeSelectAllInfo(Integer categoryId,String key,int currentPage, int pageSize, String title, @RequestParam String locale,Integer channelId) {
        PageInfo<SkChannelArticleGoodsCn> listInfo=null;
        try {
            listInfo = skChannelArticleGoodsCnService.likeSelectAll(categoryId,key,currentPage,pageSize,title,locale,channelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }


    @PostMapping("/test")
    public String test(Integer categoryId,String key,int currentPage, int pageSize, String title, @RequestParam String locale,Integer channelId) {
        PageInfo<SkChannelArticleGoodsCn> listInfo=null;
        try {
            String t="[\n" +
                    "    {\n" +
                    "        \"name\": \"彩虹\",\n" +
                    "        \"test\": \"fortest\",\n" +
                    "        \"md5\": \"34638249f5a94cadae1e6634f180e96b.svg\",\n" +
                    "        \"type\": \"sprite\",\n" +
                    "        \"tags\": [\n" +
                    "            \"people\",\n" +
                    "            \"person\",\n" +
                    "            \"drawing\"\n" +
                    "        ],\n" +
                    "        \"info\": [\n" +
                    "            0,\n" +
                    "            1,\n" +
                    "            1\n" +
                    "        ],\n" +
                    "        \"json\": {\n" +
                    "            \"objName\": \"彩虹\",\n" +
                    "            \"sounds\": [\n" +
                    "                {\n" +
                    "                    \"soundName\": \"pop\",\n" +
                    "                    \"soundID\": -1,\n" +
                    "                    \"md5\": \"83a9787d4cb6f3b7632b4ddfebf74367.wav\",\n" +
                    "                    \"sampleCount\": 258,\n" +
                    "                    \"rate\": 11025,\n" +
                    "                    \"format\": \"\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"costumes\": [\n" +
                    "                {\n" +
                    "                    \"costumeName\": \"彩虹-a\",\n" +
                    "                    \"baseLayerID\": -1,\n" +
                    "                    \"baseLayerMD5\": \"34638249f5a94cadae1e6634f180e96b.svg\",\n" +
                    "                    \"bitmapResolution\": 1,\n" +
                    "                    \"rotationCenterX\": 31,\n" +
                    "                    \"rotationCenterY\": 100\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"currentCostumeIndex\": 0,\n" +
                    "            \"scratchX\": -20,\n" +
                    "            \"scratchY\": -38,\n" +
                    "            \"scale\": 1,\n" +
                    "            \"direction\": 90,\n" +
                    "            \"rotationStyle\": \"normal\",\n" +
                    "            \"isDraggable\": false,\n" +
                    "            \"visible\": true,\n" +
                    "            \"spriteInfo\": {}\n" +
                    "        }\n" +
                    "    }\n" +
                    "]";
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            return gson.toJson(t);

        } catch (Exception e) {

            e.printStackTrace();
            return "bad";

        }
    }


    /**
     * 功能描述: 批量审核
     * @author huangwei
     * @date :2018/11/12
     * @params [id]
     * @return java.lang.String
     */
    @PutMapping("/review")
    public  String updateSt(HttpServletRequest request, @RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateSt(id,locale);
        return "批量审核";
    }
    @PutMapping("/top")
    public  String updateTo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateTo(id,locale);
        return "批量置顶";
    }
    @PutMapping("/red")
    public  String updateRe(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateRe(id,locale);
        return "批量推荐";
    }
    @PutMapping("/hot")
    public  String updateHo(HttpServletRequest request,@RequestParam String locale){
        String id = request.getParameter("id");
        skChannelArticleGoodsCnService.updateHo(id,locale);
        return "批量热门";
    }


}

package com.niit.service.cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;
import com.niit.service.cms.pojo.SkChannelArticleGoodsOrder;
import com.niit.service.cms.service.SkChannelArticleGoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.28 17:01
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.28 17:01
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order_goods_costumes")
public class SkChannelArticleGoodsOrderController {

    @Autowired
    SkChannelArticleGoodsOrderService skChannelArticleGoodsOrderService;


    @PostMapping
    public String generateOrders(@RequestBody SkChannelArticleGoodsOrder record) {
        return skChannelArticleGoodsOrderService.generateOrders(record).toString();
    }

    @GetMapping("/check_owned")
    public String checkIsOwned(Integer userId, Integer goodId) {
        return skChannelArticleGoodsOrderService.checkIsOwned(userId, goodId).toString();
    }

    @GetMapping
    public String selectMyLibrary(Integer userId) {
        List<SkChannelArticleGoodsCostumes> list;
        //拿到所有的造型(是costumes,非goods)
        list = skChannelArticleGoodsOrderService.selectByUserId(userId);

        //以下代码把从数据库查到的costumes列表格式化成Scratch所需要的结果格式，否则scratch会报错
        //resultList为Scratch所需要的结果格式
        List<Map<String, Object>> resultList = new LinkedList<>();

        //拿到所有的goodsId（此步可优化：上一步在批量查询造型的时候，用的就是goodsId）
        Set<Integer> goodIdSet = new HashSet<>();
        list.stream().forEach(e -> goodIdSet.add(e.getGoodId()));

        //一个商品（good）对应一个sprite(scratch角色)对应多个造型（costumes）与音频
        for (Integer i : goodIdSet) {
            //所有sprites(scratch角色)Map
            Map<String, Object> resultMap = new LinkedHashMap<>();
            //单个sprite(scratch角色)的子类Map（包含了造型与音频）的信息与其他杂七杂八的属性
            Map<String, Object> jsonMap = new LinkedHashMap<>();
            //costumes(造型)List
            List<Map<String, Object>> costumesList = new LinkedList<>();
            // TODO sounds

            //把属于同一个sprites(scratch角色)的造型加入到costumes(造型)Map
            list.stream().forEach(e -> {
                if (i == e.getGoodId()) {
                    // TODO sounds
                    //把单个costume(造型)的信息放入costumes(造型)List
                    Map<String, Object> costumeMap = new LinkedHashMap<>();
                    costumeMap.put("costumeName", e.getCostumename());
                    costumeMap.put("baseLayerMD5", e.getBaselayermd5());
                    costumeMap.put("bitmapResolution", e.getBitmapresolution());
                    costumeMap.put("rotationCenterX", e.getRotationcenterx());
                    costumeMap.put("rotationCenterY", e.getRotationcentery());
                    costumesList.add(costumeMap);
                }
            });

            Map<Integer, Object> spriteInfoMap = new LinkedHashMap<>();

            //拿到第一个costume(造型)作为"门面"
            SkChannelArticleGoodsCostumes costume = list.stream().filter(e -> e.getGoodId() == i).findFirst().get();

            //标签，必须为List
            //TODO 区分背景library与角色library
            List<String> tagList = new LinkedList<>();
            tagList.add("mylibrary");

            List<Integer> infoList = new LinkedList<>();
            //几个音频文件
            infoList.add(0);
            //几个造型
            infoList.add(costumesList.size());
            //？？？不知道第三个属性是什么意思
            infoList.add(1);

            jsonMap.put("objName", costume.getCostumename());
            jsonMap.put("costumes", costumesList);
            jsonMap.put("currentCostumeIndex", costume.getCurrentcostumeindex());
            jsonMap.put("scratchX", costume.getScratchx());
            jsonMap.put("scratchY", costume.getScratchy());
            jsonMap.put("scale", costume.getScale());
            jsonMap.put("direction", costume.getDirection());
            jsonMap.put("rotationStyle", costume.getRotationstyle());
            jsonMap.put("isDraggable", costume.getIsdraggable());
            jsonMap.put("visible", costume.getVisible());
            jsonMap.put("spriteInfo", spriteInfoMap);

            resultMap.put("name", costume.getCostumename());
            resultMap.put("md5", costume.getMd5());
            resultMap.put("type", costume.getType());
            resultMap.put("tags", tagList);
            resultMap.put("info", infoList);
            resultMap.put("json", jsonMap);

            //把各个素材信息加入到resultList
            resultList.add(resultMap);

        }

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(resultList);
    }

}

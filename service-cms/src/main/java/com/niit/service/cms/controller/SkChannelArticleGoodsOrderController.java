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
    public String generateOrders(@RequestBody SkChannelArticleGoodsOrder record){
        return  skChannelArticleGoodsOrderService.generateOrders(record).toString();
    }

    @GetMapping("/check_owned")
    public String checkIsOwned(Integer userId,Integer goodId){
        return skChannelArticleGoodsOrderService.checkIsOwned(userId, goodId).toString();
    }

    @GetMapping
    public String selectMyLibrary(Integer userId){
        List<SkChannelArticleGoodsCostumes> list;
        list =skChannelArticleGoodsOrderService.selectByUserId(userId);

        List<Map<String,Object>> resultList=new LinkedList<>();

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

        Set<Integer> goodIdSet=new HashSet<>();

        list.stream().forEach(e->{
            goodIdSet.add(e.getGoodId());
        });


        for(Integer i:goodIdSet){
            Map<String,Object> jsonMap=new LinkedHashMap<>();
            Map<String,Object> resultMap=new LinkedHashMap<>();
            List<Map<String,Object>> costumesList=new LinkedList<>();

            System.out.println(i);
            list.stream().forEach(e->{
                if(i==e.getGoodId()){
                    Map<String,Object> costumeMap=new LinkedHashMap<>();
                    costumeMap.put("costumeName",e.getCostumename());
                    costumeMap.put("baseLayerMD5",e.getBaselayermd5());
                    costumeMap.put("bitmapResolution",1);
                    costumeMap.put("rotationCenterX",31);
                    costumeMap.put("rotationCenterY",100);
                    costumesList.add(costumeMap);
                }
            });

            List<String> tagList=new LinkedList<>();
            tagList.add("animals");
            tagList.add("mylibrary");
            List<Integer> infoList=new LinkedList<>();
            infoList.add(0);
            infoList.add(2);
            infoList.add(1);

            Map<Integer,Object> spriteInfoMap=new LinkedHashMap<>();
            // TODO sounds
            jsonMap.put("objName",list.stream().filter(e->e.getGoodId()==i).findFirst().get().getCostumename());
            jsonMap.put("costumes",costumesList);
            jsonMap.put("currentCostumeIndex",0);
            jsonMap.put("scratchX",-20);
            jsonMap.put("scratchY",-38);
            jsonMap.put("scale",1);
            jsonMap.put("direction",90);
            jsonMap.put("rotationStyle","normal");
            jsonMap.put("isDraggable",false);
            jsonMap.put("visible",true);
            jsonMap.put("spriteInfo",spriteInfoMap);

            //TODO 这个name应该是goods表里的name
            resultMap.put("name",list.stream().filter(e->e.getGoodId()==i).findFirst().get().getCostumename());
            resultMap.put("md5",list.stream().filter(e->e.getGoodId()==i).findFirst().get().getMd5());
            resultMap.put("type",list.stream().filter(e->e.getGoodId()==i).findFirst().get().getType());
            resultMap.put("tags",tagList);
            resultMap.put("info",infoList);
            resultMap.put("json",jsonMap);
            resultList.add(resultMap);

        }


        return gson.toJson(resultList);
    }

}

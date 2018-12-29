package com.niit.service.cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;
import com.niit.service.cms.service.SkChannelArticleGoodsCostumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.28 15:43
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.28 15:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RestController
@RequestMapping("/goods_costumes")
public class SkChannelArticleGoodsCostumesController {

    @Autowired
    SkChannelArticleGoodsCostumesService skChannelArticleGoodsCostumesService;


    @GetMapping
    public String selectByGoodId(Integer goodId){
        List<SkChannelArticleGoodsCostumes> list;
        list= skChannelArticleGoodsCostumesService.selectByGoodId(goodId);

        Map<String,Object> jsonMap=new LinkedHashMap<>();

        List<Map<String,Object>> costumesList=new LinkedList<>();

        list.stream().forEach(e->{
            Map<String,Object> costumeMap=new LinkedHashMap<>();
            costumeMap.put("costumeName",e.getCostumename());
            costumeMap.put("baseLayerMD5",e.getBaselayermd5());
            costumeMap.put("bitmapResolution",1);
            costumeMap.put("rotationCenterX",31);
            costumeMap.put("rotationCenterY",100);
            costumesList.add(costumeMap);
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
        jsonMap.put("objName",list.get(0).getCostumename());
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

        Map<String,Object> resultMap=new LinkedHashMap<>();
        //TODO 这个name应该是goods表里的name
        resultMap.put("name",list.get(0).getCostumename());
        resultMap.put("md5",list.get(0).getMd5());
        resultMap.put("type",list.get(0).getType());
        resultMap.put("tags",tagList);
        resultMap.put("info",infoList);
        resultMap.put("json",jsonMap);


        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

        return gson.toJson(resultMap);
    }
}

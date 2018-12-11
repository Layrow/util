package com.niit.website.smartkids.controller;

import com.niit.website.smartkids.service.SkchannelArticleVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/smartkids/video")
public class SkchannelArticleVideoController {
    @Autowired
    SkchannelArticleVideoService skchannelArticleVideoService;

    @GetMapping(value = "/category")
    public Integer getByCategory(String key,Integer pageSize,Integer currentPage,Integer categoryId){
//        skchannelArticleVideoService.
        return 1;
    }



}

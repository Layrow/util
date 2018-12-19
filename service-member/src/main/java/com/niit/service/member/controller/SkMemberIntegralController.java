package com.niit.service.member.controller;

import com.niit.service.member.service.ISkMemberIntegralService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/18
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/integral")
public class SkMemberIntegralController {
    @Resource
    ISkMemberIntegralService skMemberIntegralService;

    @PostMapping("/tatal")
    public int tatal(Integer uid){
        return  skMemberIntegralService.getMemberIntegral(uid);
    }
}

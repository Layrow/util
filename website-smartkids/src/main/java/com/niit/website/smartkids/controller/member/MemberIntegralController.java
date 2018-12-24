package com.niit.website.smartkids.controller.member;

import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.service.memberservice.IMemberItegralService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/19
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/integral")
public class MemberIntegralController {
    @Resource
    IMemberItegralService memberItegralService;

    @GetMapping("/total")
    public String total(@RequestParam Integer uId){
           return memberItegralService.getTatal(uId) ;

    }
    @PostMapping("integral")
    public  String integral (@RequestBody SkMemberIntegral record){
         return memberItegralService.interAction(record);
    }
}

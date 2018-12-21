package com.niit.website.smartkids.controller.member;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationOps;
import com.niit.website.smartkids.service.memberservice.IMemberNoficationOps;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/notification")
public class SkMemberNotificationController {

    @Resource
    IMemberNoficationOps notificationService;

    /**
     * 添加一个通知
     * @param record
     * @return
     */
    @PostMapping("/add")
    public boolean add(@RequestBody SkMemberNotificationOps record){
        return notificationService.insertNotification(record);
    }

    /**
     * 删除用户列表下的 一条通知
     * @param nId
     * @return
     */
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Integer nId){
        return notificationService.deleteNotification(nId);
    }

    @PostMapping("/listAll")
    public PageInfo<SkMemberNotificationOps> listAll(@RequestParam Integer currentPage,@RequestParam Integer pageSize,@RequestParam Integer uId){
        return notificationService.listAll(currentPage,pageSize,uId);
    }


}

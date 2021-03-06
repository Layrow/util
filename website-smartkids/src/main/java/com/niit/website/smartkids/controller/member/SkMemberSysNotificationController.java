package com.niit.website.smartkids.controller.member;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationSystem;
import com.niit.website.smartkids.service.memberservice.IMemberNotificationSys;
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
@RequestMapping("/system")
public class SkMemberSysNotificationController {
    @Resource
    IMemberNotificationSys sysNotificationService;

    @PostMapping("add")
    public boolean add(@RequestBody SkMemberNotificationSystem record) {
        return sysNotificationService.add(record);
    }

    @DeleteMapping("delete")
    public boolean delete(@RequestParam String sId) {
        return sysNotificationService.delete(sId);
    }

    @PutMapping("edit")
    public boolean edit(@RequestBody SkMemberNotificationSystem record) {
        return sysNotificationService.update(record);
    }

    @PostMapping("listAll")
    public PageInfo<SkMemberNotificationSystem> list(Integer currentPage, Integer pageSize) {
        return sysNotificationService.listAll(currentPage, pageSize);
    }
}

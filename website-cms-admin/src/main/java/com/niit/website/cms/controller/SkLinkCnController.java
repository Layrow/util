package com.niit.website.cms.controller;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkLinkCn;
import com.niit.website.cms.service.SkLinkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 链接管理控制器
 * @Author: yuwentao&fengyonghui
 * @CreateDate: 2018/11/6 11:00
 * @UpdateUser: yuwentao&fengyonghui
 * @UpdateDate: 2018/11/6 11:00
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RestController
public class SkLinkCnController {
    @Autowired
    SkLinkCnService skLinkCnService;


    /**
     * @Description 插入单条链接
     * @author      yuwentao
     * @param       skLinkCn
     */
    @PostMapping(value = "/link")
    public Integer insert(@RequestBody SkLinkCn skLinkCn, @RequestParam String locale) {
//        String locale=request.getParameter("locale");
        Integer insertStatus = 0;
        try {
            insertStatus = skLinkCnService.insert(skLinkCn,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * @Description 更新单条链接
     * @author      yuwentao
     * @param       skLinkCn
     */
    @PutMapping(value = "/link")
    public Integer update(@RequestBody SkLinkCn skLinkCn,  @RequestParam String locale) {
        Integer updateStatus = 0;
        try {
            updateStatus = skLinkCnService.updateByPrimaryKey(skLinkCn,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    /**
     * @param currentPage 当前页码
     * @param pageSize    单页数据量
     * @Description 分页查询
     * @author yuwentao
     */
    @GetMapping(value="/link")
    public PageInfo<SkLinkCn> selectByPage(@RequestParam int currentPage, int pageSize, @RequestParam String locale) {
        PageInfo<SkLinkCn> listInfo=null;
        try {
            listInfo = skLinkCnService.selectByPage(currentPage,pageSize,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }

    /**
     * @Description 批量更新多条链接
     * @author yuwentao
     * @param updateList
     * @param locale
     */
    @PutMapping(value = "/linklist")
    public Integer updateByList(@RequestBody List<SkLinkCn> updateList, @RequestParam String locale) {
        Integer updateStatus = 0;
        try {
            updateStatus = skLinkCnService.updateByList(updateList,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }


    /**
     * 删除操作
     * @param ids
     * @return
     */
    @DeleteMapping("/delLink")
    public String delete(@RequestParam String ids, @RequestParam String locale) {
        skLinkCnService.deleteBatchLinkByPromaryKey(ids,locale);
        return ids;
    }

    /**
     * 检查操作
     * @param ids
     * @return
     */
    @PutMapping("/checkLink")
    public String check(@RequestParam String ids,@RequestParam String locale) {
        skLinkCnService.check(ids,locale);
        return ids;
    }

    /**
     *  分页模糊查询
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param title 搜索关键字
     * @param locale 语言
     * @return
     */
    @PostMapping("/link/likeSelectAll")
    public PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize, String title,@RequestParam String locale){
        return skLinkCnService.likeSelectAll(currentPage,pageSize,title,locale);
    }

    /**
     * 分页模糊查询
     * @param currentPage
     * @param pageSize
     * @param locale
     * @return
     */
    @PostMapping("/link/listAllLink")
    public PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize, @RequestParam String locale){
        return skLinkCnService.listAllLink(currentPage,pageSize,locale);
    }
}


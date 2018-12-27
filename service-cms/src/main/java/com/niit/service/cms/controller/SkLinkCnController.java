package com.niit.service.cms.controller;


import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkLinkCn;
import com.niit.service.cms.service.SkLinkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * @Description: 链接管理控制器
 * @Author: yuwentao&fengyonghui
 * @CreateDate: 2018/11/6 10:58
 * @UpdateUser: yuwentao&fengyonghui
 * @UpdateDate: 2018/11/6 10:58
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RestController
public class SkLinkCnController {
    @Autowired
    SkLinkCnService skLinkCnService;

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
     * @Description 插入单条链接
     * @author      yuwentao
     * @param       skLinkCn
     */
    @PostMapping(value = "/link")
    public Integer insert(@RequestBody SkLinkCn skLinkCn,@RequestParam String locale) {
        skLinkCn.setAddTime(new Date());
        Integer insertStatus = 0;
        try {
            insertStatus = skLinkCnService.insert(skLinkCn,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * @param currentPage 当前页
     * @param pageSize    单页数据量
     * @Description 分页查询
     * @author yuwentao
     */
    @GetMapping(value="/link")
    public PageInfo<SkLinkCn> selectByPage(@RequestParam int currentPage, int pageSize,String locale) {

        PageInfo<SkLinkCn> listInfo=null;
        try {
            listInfo = skLinkCnService.selectByPage(currentPage,pageSize,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }


    /**
     * @Description 更新单条链接
     * @author      yuwentao
     * @param       skLinkCn
     */
    @PutMapping(value = "/link")
    public Integer update(@RequestBody SkLinkCn skLinkCn,@RequestParam String locale) {
        Integer updateStatus = 0;
        try {
            updateStatus = skLinkCnService.updateByPrimaryKey(skLinkCn,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
    /**
     * @Description 更新多条链接
     * @author      冯永辉
     * @param       list
     */
    @PutMapping(value = "/updateList")
    public Integer updateList(@RequestBody List<SkLinkCn> list,@RequestParam String locale) {
        try {
            Iterator<SkLinkCn> iterator=list.iterator();
            while (iterator.hasNext()){
                skLinkCnService.updateByPrimaryKey(iterator.next(),locale);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 访问路径为/linkList 完成批量审核
     * @param linkID 从客户端获取 传递的LinkID
     * @return 成功返回true
     */
    @PutMapping("/checkLink")
    public String check(@RequestParam String linkID,String locale) {
        skLinkCnService.check(linkID,locale);
        return linkID;
    }
    /**
     * 访问路径为/linkList 完成批量审核
     * @param linkID 从客户端获取 传递的LinkID
     * @return 成功返回true
     */
    @DeleteMapping("/delLink")
    public String delete(@RequestParam String linkID,@RequestParam String locale) {
        skLinkCnService.deleteBatchLinkByPromaryKey(linkID,locale);
        return linkID;
    }

    /**
     * 模糊查询 全部数据
     * @param currentPage  当前页面
     * @param pageSize  页面大小
     * @param title  查询关键字
     * @param locale  选择语言
     * @return 分页之后的数据
     */
    @PostMapping("/link/likeSelectAll")
    public PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize, String title,@RequestParam String locale){
        PageInfo<SkLinkCn> list=null;
        try {
            list =skLinkCnService.likeSelectAll(currentPage,pageSize,title,locale);
        }catch (Exception e){
            e.printStackTrace();
        }
            return list;
    }

    /**
     * 分页查询所有
     * @param currentPage
     * @param pageSize
     * @param locale
     * @return
     */
    @PostMapping("/link/listAllLink")
    public PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize, @RequestParam String locale){
        PageInfo<SkLinkCn> list=null;
        try {
            list =skLinkCnService.listAllLink(currentPage,pageSize,locale);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}


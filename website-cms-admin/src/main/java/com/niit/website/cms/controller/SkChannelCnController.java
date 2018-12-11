package com.niit.website.cms.controller;

import com.niit.website.cms.pojo.SkChannelCn;
import com.niit.website.cms.service.SkChannelCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SkChannelCnController
 * @Description 插入频道类别
 * @Author liyuhao
 * @Date 2018/10/30 20:01
 **/
@CrossOrigin
@RestController
@RequestMapping("/skChannelCn")
public class SkChannelCnController {

    @Autowired
    private SkChannelCnService skChannelCnService;

    /**
     * @Description 新增channel
     * @Date 2018/10/31 17:12
     * @Param [record]
     * @Return int
     **/
    @PostMapping("/channel/{locale}")
    public Integer insertSkChannelCn(@RequestBody SkChannelCn record,@PathVariable("locale") String locale) {
        // 操作成功返回1，失败返回0
        Integer insertStatus = 0;
        try {
            insertStatus = skChannelCnService.insertSelective(record,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * @Description 查询所有channel
     * @Date 2018/10/31 9:28
     * @Param []
     * @Return java.util.List<com.niit.service.cms.pojo.SkChannelCn>
     **/
    @GetMapping("/channel/{locale}")
    public List<SkChannelCn> selectAllChannelCn(@PathVariable("locale") String locale) {
        List<SkChannelCn> skChannelCnList = null;
        try {
            skChannelCnList = skChannelCnService.selectAllChannel(locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skChannelCnList;
    }

    /**
     * @Description 修改channel
     * @Date 2018/10/31 10:10
     * @Param [record]
     * @Return java.lang.String
     **/
    @PutMapping("/channel/{locale}")
    public Integer updateChannelCn(@RequestBody SkChannelCn record,@PathVariable("locale") String locale) {
        Integer updateStatus = 0;
        try {
            updateStatus = skChannelCnService.updateByPrimaryKeySelective(record,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    /**
     * @Description 删除单条channel
     * @Date 2018/10/31 10:13
     * @Param [id]
     * @Return java.lang.String
     **/
    /*@DeleteMapping("/channel/{id}")
    public Integer deleteChannelCn(@PathVariable("id") int id) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skChannelCnService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }*/

    /**
     * @Description 批量删除channel 传入的参数不一样
     * @Date 2018/10/31 10:36
     * @Param [channelArr]
     * @Return java.lang.String
     **/
    /*@DeleteMapping("/channel")
    public Integer deleteMoreChannelCn(HttpServletRequest request) {
        Integer deleteBatchStatus = 0;
        // 获取多个id  1,2,3(String类型)
        String id = request.getParameter("id");
        try {
            deleteBatchStatus = skChannelCnService.deleteMoreChannel(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteBatchStatus;
    }*/

    /**
     * @Description 批量删除channel 也能删除单条记录 访问形式skChannelCn/channel/9,10,11
     * @Date 2018/11/1 16:34
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @DeleteMapping("/channel/{locale}/{id}")
    public Integer deleteMoreChannelCn(@PathVariable("id") String id,@PathVariable("locale") String locale) {
        Integer deleteBatchStatus = 0;
        // 获取多个id  1,2,3(String类型)
        try {
            deleteBatchStatus = skChannelCnService.deleteMoreChannel(id,locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteBatchStatus;
    }

}



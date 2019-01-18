package com.niit.service.lms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.pojo.SkLmsCoursewareCn;
import com.niit.service.lms.service.SkLmsCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 15:55
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
public class SkLmsCoursewareCnController {

    @Autowired
    private SkLmsCoursewareCnService sccs;

    /**
     * 功能描述:课件管理 单个删除
     *
     * @return java.lang.Integer
     * @author huangwei
     * @date 2018/11/8
     * @params [id]
     */
    @DeleteMapping
    public Integer deleteByPrimaryKeyInfo(Integer id) {
        int i = sccs.deleteByPrimaryKey(id);
        return i;
    }

    /**
     * 功能描述:课件模糊查询
     *
     * @return java.util.List<com.niit.service.lms.pojo.SkLmsCoursewareCn>
     * @author huangwei
     * @date :2018/11/12
     * @params [title]
     */
    @GetMapping
    public String selectAllInfo(Integer facultyId, int currentPage, int pageSize, String courseware_title) {
        String s = sccs.selectAll(facultyId, currentPage, pageSize, courseware_title);
        return s;

    }

    /**
     * 功能描述: 上传文件
     *
     * @return int
     * @author huangwei
     * @date :2018/11/15
     * @params [request]
     */


    //根据老师插查询所有
    @GetMapping("/all")
    public String selectAllWare(Integer facultyId, int currentPage, int pageSize) {
        return sccs.selectAllWare(facultyId, currentPage, pageSize);
    }

    /**
     * 功能描述: 查询制定id班级下所有课件
     *
     * @return java.util.List<com.niit.service.lms.pojo.SkLmsCoursewareCn>
     * @author huangwei
     * @date : 2018/11/20
     * @params [batchId]
     */
    @GetMapping("/allBatch")
    public String selectAllBatchWare(Integer batchId, int currentPage, int pageSize) {
        return sccs.selectAllBatchWare(batchId, currentPage, pageSize);
    }

    @PostMapping
    public String insertSelectiveInfo(@RequestBody SkLmsCoursewareCn record) {

        record.setCoursewareCreateTime(new Date());
        record.setCoursewareStatus(1);
        int i = sccs.insertCn(record);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);

    }
}




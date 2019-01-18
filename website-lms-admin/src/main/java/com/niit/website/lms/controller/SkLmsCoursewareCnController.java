package com.niit.website.lms.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.website.lms.pojo.SkLmsCoursewareCn;
import com.niit.website.lms.service.SkLmsCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date:2018/11/12 10:56
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
public class SkLmsCoursewareCnController {
    @Autowired
    private SkLmsCoursewareCnService ccs;

    /**
     * 功能描述:根据ID进行删除
     *
     * @return int
     * @author huangwei
     * @date :2018/11/12
     * @params [id]
     */
    @DeleteMapping
    public int deleteByPrimaryInfo(Integer id) {
        int i = ccs.deleteByPrimaryKey(id);
        return i;

    }

    /**
     * 功能描述: 共享
     *
     * @return java.util.List<com.niit.website.lms.pojo.SkLmsCoursewareCn>
     * @author huangwei
     * @date :2018/11/20
     * @params [facultyId, courseware_title]
     */
    @GetMapping
    public Map<String, Object> selectAll(Integer facultyId, int currentPage, int pageSize, String courseware_title) {
        Map<String, Object> map = null;
        String ware = ccs.selectAll(facultyId, currentPage, pageSize, courseware_title);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Gson gson = new Gson();
        if (ware == null || ware.isEmpty()) {
            return map;
        }
        map = gson.fromJson(ware, type);
        return map;
    }

    /**
     * 功能描述:上传文件
     *
     * @return java.lang.Integer
     * @author huangwei
     * @date :2018/11/19
     * @params [url]
     */
    @PostMapping
    public String insertSelectiveInfo(@RequestBody SkLmsCoursewareCn record) {
        return ccs.insertCn(record);

    }

    /**
     * 功能描述:查询所有课件
     *
     * @return java.util.List<com.niit.website.lms.pojo.SkLmsCoursewareCn>
     * @author huangwei
     * @date :2018/11/19
     * @params []
     */
    @GetMapping("/all")
    public Map<String, Object> selectAllWare(Integer facultyId, int currentPage, int pageSize) {
        Map<String, Object> map = null;
        String ware = ccs.selectAllWare(facultyId, currentPage, pageSize);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Gson gson = new Gson();
        if (ware == null || ware.isEmpty()) {
            return map;
        }
        map = gson.fromJson(ware, type);
        return map;

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
    public Map<String, Object> selectAllBatchWare(Integer batchId, int currentPage, int pageSize) {
        Map<String, Object> map = null;
        String ware = ccs.selectAllBatchWare(batchId, currentPage, pageSize);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Gson gson = new Gson();
        if (ware == null || ware.isEmpty()) {
            return map;
        }
        map = gson.fromJson(ware, type);
        return map;

    }


}

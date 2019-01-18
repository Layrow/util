package com.niit.service.lms.controller;

import com.niit.service.lms.pojo.SkLmsBatchCn;
import com.niit.service.lms.service.SkLmsBatchCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SkLmsBatchCnController
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 13:37
 **/
@CrossOrigin
@RestController
@RequestMapping("/skLmsBatchCn")
public class SkLmsBatchCnController {

    @Autowired
    private SkLmsBatchCnService skLmsBatchCnService;

    /**
     * @Description 创建班级
     * @Date 2018/11/8 13:44
     * @Param [skLmsBatchCn]
     * @Return java.lang.Integer
     **/
    @PostMapping("/batch")
    public Integer insertBatch(@RequestBody SkLmsBatchCn skLmsBatchCn) {
        Integer insertStatus = 0;
        try {
            // 封装班级对象
            SkLmsBatchCn batchCn = new SkLmsBatchCn();
            batchCn.setBatchCreateTime(new Date());
            batchCn.setFacultyId(skLmsBatchCn.getFacultyId());
            batchCn.setBatchName(skLmsBatchCn.getBatchName());
            batchCn.setBatchStatus(1);
            insertStatus = skLmsBatchCnService.insertSelective(batchCn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * @Description 传入所属教师ID，查询该教师创建的所有班级
     * @Date 2018/11/8 13:48
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @GetMapping("/batch/{facultyId}")
    public List<SkLmsBatchCn> selectBatch(@PathVariable Integer facultyId) {
        List<SkLmsBatchCn> skLmsBatchCnList = null;
        try {
            skLmsBatchCnList = skLmsBatchCnService.selectByFacultyId(facultyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skLmsBatchCnList;
    }

    /**
     * @Description 修改班级信息
     * @Date 2018/11/8 14:01
     * @Param [skLmsBatchCn]
     * @Return java.lang.Integer
     **/
    @PutMapping("/batch")
    public Integer updateBatch(@RequestBody SkLmsBatchCn skLmsBatchCn) {
        Integer updateStauts = 0;
        try {
            updateStauts = skLmsBatchCnService.updateByPrimaryKeySelective(skLmsBatchCn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStauts;
    }

    /**
     * @Description 删除班级
     * @Date 2018/11/8 14:04
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @DeleteMapping("/batch/{id}")
    public Integer deleteBatch(@PathVariable Integer id) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skLmsBatchCnService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }
}

package com.niit.service.lms.controller;

import com.niit.service.lms.service.SkLmsHomeworkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SkLmsHomeworkCnController
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 15:32
 **/
@CrossOrigin
@RestController
@RequestMapping("/skLmsHomeworkCn")
public class SkLmsHomeworkCnController {

    @Autowired
    private SkLmsHomeworkCnService skLmsHomeworkCnService;

    /**
     * @Description 查询特定的老师布置的作业
     * @Date 2018/11/8 15:49
     * @Param [facultyId]
     * @Return java.util.List<com.niit.service.lms.pojo.SkLmsHomeworkCn>
     **/
    @GetMapping("/homework/batch/{batchId}")
    public String selectHomework(@PathVariable Integer batchId) {
        return skLmsHomeworkCnService.selectHomeworkByBatchId(batchId);
    }

    /**
     * @Description 删除作业
     * @Date 2018/11/8 15:53
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @DeleteMapping("/homework/{id}")
    public void deleteHomework(@PathVariable Integer id) {
        try {
            skLmsHomeworkCnService.deleteHomework(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 修改作业
     * @Date 2018/11/8 15:55
     * @Param [skLmsHomeworkCn]
     * @Return java.lang.Integer
     **/
    @PutMapping("/homework")
    public void updateHomework(@RequestBody String json) {
        try {
            skLmsHomeworkCnService.updateHomework(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description 布置作业
     * @Date 2018/11/8 15:57
     * @Param [skLmsHomeworkCn]
     * @Return java.lang.Integer
     **/
    @PostMapping("/homework")
    public Integer insertHomework(@RequestBody String json) throws Exception {
        try {
            skLmsHomeworkCnService.insertHomework(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * @Description 查询特定作业信息
     * @Date 2018/11/19 15:44
     * @Param [homeworkId]
     * @Return com.niit.service.lms.pojo.SkLmsHomeworkCn
     **/
    @GetMapping("/homework/{homeworkId}")
    public String selectHomeworkById(@PathVariable Integer homeworkId) {
        return skLmsHomeworkCnService.selectByPrimaryKey(homeworkId);
    }

    /**
     * @Description 查询特定作业下有哪些学生提交
     * @Date 2018/11/19 19:28
     * @Param [homeworkId]
     * @Return java.util.List<com.niit.service.lms.pojo.SkLmsStudentsCn>
     **/
    @GetMapping("/homework/stu")
    public String selectStudentByHomeworkId(Integer batchId, Integer homeworkId, Integer currentPage, Integer pageSize) {
        return skLmsHomeworkCnService.selectStudentByHomeworkId(batchId,homeworkId,currentPage,pageSize);
    }

}

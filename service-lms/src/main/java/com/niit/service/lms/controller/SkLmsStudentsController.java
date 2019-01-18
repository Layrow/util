package com.niit.service.lms.controller;

import com.github.pagehelper.PageInfo;
import com.niit.service.lms.pojo.SkLmsBatchHomeworkCn;
import com.niit.service.lms.pojo.SkLmsCoursewareCn;
import com.niit.service.lms.pojo.SkLmsHomeworkCn;
import com.niit.service.lms.service.SkLmsStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.08 12:23
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.08 12:23
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/student")
public class SkLmsStudentsController {

    @Autowired
    SkLmsStudentsService skLmsStudentsService;

    //查找某班作业要求列表
    @GetMapping(value = "/homework/{batch_id}")
    public List<SkLmsHomeworkCn> selectHomeworkByBatch(@PathVariable int batch_id) {

        List<SkLmsHomeworkCn> list = null;
        try {
            list = skLmsStudentsService.selectHomeworkByBatch(batch_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //查找某班课件列表
    @GetMapping(value = "/courseware/{batch_id}")
    public PageInfo<SkLmsCoursewareCn> selectCoursewareByBatch(@PathVariable int batch_id, Integer currentPage, Integer pageSize) {

        PageInfo<SkLmsCoursewareCn> pageInfo = null;
        try {
            pageInfo = skLmsStudentsService.selectCoursewareByBatch(batch_id, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }


    //查找单项作业要求
    @GetMapping(value = "/homework/*/{homework_id}")
    public SkLmsHomeworkCn selectHomeworkById(@PathVariable int homework_id) {
        SkLmsHomeworkCn homeworkInfo = null;
        try {
            homeworkInfo = skLmsStudentsService.selectHomeworkById(homework_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return homeworkInfo;
    }

    //查找单项学生作业
    @GetMapping(value = "/homework/*/{homework_id}/{student_id}")
    public String selectHomeworkByStudentAndHomeworkId(@PathVariable int homework_id, @PathVariable int student_id) {
        String totalString = null;
        try {
            totalString = skLmsStudentsService.selectHomeworkAnswerById(homework_id, student_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalString;
    }

    //提交作业
    @PostMapping(value = "/homework/*/*")
    public Integer insertHomework(@RequestBody String json) {
        Integer status = 0;
        try {
            status = skLmsStudentsService.insertStudentHomework(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }


    //编辑作业(必须传homework_id)
    @PutMapping(value = "/homework/*/*")
    public Integer updateHomework(@RequestBody String json) {
        Integer status = 0;
        try {
            status = skLmsStudentsService.updateHomeworkAnswer(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    //布置作业
    @PostMapping(value = "/testInsert")
    public Integer testInsert(@RequestBody SkLmsHomeworkCn skLmsHomeworkCn) {
        Integer status = 0;
        try {
            status = skLmsStudentsService.testInsert(skLmsHomeworkCn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    //分配作业到班级
    @PostMapping(value = "/testInsert2")
    public Integer testInsert2(@RequestBody SkLmsBatchHomeworkCn skLmsBatchHomeworkCn) {
        Integer status = 0;
        try {
            status = skLmsStudentsService.testInsert2(skLmsBatchHomeworkCn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


}

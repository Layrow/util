package com.niit.website.lms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.niit.website.lms.pojo.SkLmsCoursewareCn;
import com.niit.website.lms.pojo.SkLmsHomeworkCn;
import com.niit.website.lms.service.SkLmsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.09 09:43
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.09 09:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class SkLmsStudentController {

    @Autowired
    SkLmsStudentService skLmsStudentsService;

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

        PageInfo<SkLmsCoursewareCn> list = null;
        try {
            list = skLmsStudentsService.selectCoursewareByBatch(batch_id,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
    public Map<String, Object> selectHomeworkByStudentAndHomeworkId(@PathVariable int homework_id, @PathVariable int student_id) {
        Map<String, Object> totalMap = null;
        try {
            String totalString = skLmsStudentsService.selectHomeworkAnswerById(homework_id, student_id);
            //把String转成Map
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (totalString == null || totalString.isEmpty()) {
                return totalMap;
            }
            totalMap = gson.fromJson(totalString, type);
        } catch (Exception e) {
            e.printStackTrace();
            return totalMap;
        }
        return totalMap;
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


}

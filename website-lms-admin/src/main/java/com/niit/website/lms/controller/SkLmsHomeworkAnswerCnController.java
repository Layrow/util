package com.niit.website.lms.controller;

import com.niit.website.lms.pojo.SkLmsHomeworkAnswerCn;
import com.niit.website.lms.service.SkLmsHomeworkAnswerCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SkLmsHomeworkAnswerCnController
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 10:14
 **/
@CrossOrigin
@RestController
@RequestMapping("/skLmsHomeworkAnswerCn")
public class SkLmsHomeworkAnswerCnController {
    @Autowired
    private SkLmsHomeworkAnswerCnService skLmsHomeworkAnswerCnService;

    @GetMapping("/homeworkanswer/{studentId}/{homeworkId}")
    public String selectAnswerInfoByHomeIdAndStuId(@PathVariable Integer studentId,@PathVariable Integer homeworkId) {
        String info = null;
        try {
            info = skLmsHomeworkAnswerCnService.selectAnswerInfoByHomeIdAndStuId(studentId, homeworkId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @PutMapping("/homeworkanswer")
    public void updateAnswerInfo(@RequestBody SkLmsHomeworkAnswerCn skLmsHomeworkAnswerCn) {
        skLmsHomeworkAnswerCnService.updateByPrimaryKeySelective(skLmsHomeworkAnswerCn);
    }

    @GetMapping("/analyse")
    public String selectStudentHomeworkInfo(@RequestParam("batchId")  Integer batchId,@RequestParam("studentId") Integer studentId) {
        return skLmsHomeworkAnswerCnService.selectStudentHomeworkInfo(batchId, studentId);
    }

    @GetMapping("/analyse/batch")
    public String selectBatchInfo(@RequestParam("batchId") Integer batchId) {
        return skLmsHomeworkAnswerCnService.selectBacthInfo(batchId);
    }
}

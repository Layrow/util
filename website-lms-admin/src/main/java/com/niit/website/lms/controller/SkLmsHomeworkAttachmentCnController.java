package com.niit.website.lms.controller;

import com.niit.website.lms.service.SkLmsHomeworkAttachmentCnSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SkLmsHomeworkAttachmentCnController
 * @Description
 * @Author liyuhao
 * @Date 2018/11/16 13:48
 **/
@CrossOrigin
@RestController
@RequestMapping("/skLmsHomeworkAttachmentCn")
public class SkLmsHomeworkAttachmentCnController {

    @Autowired
    private SkLmsHomeworkAttachmentCnSerive skLmsHomeworkAttachmentCnSerive;

    @DeleteMapping("/homeworkAttachment/{id}")
    public void deleteSkLmsHomeworkAttachment(@PathVariable Integer id) {
        skLmsHomeworkAttachmentCnSerive.deleteByPrimaryKey(id);
    }
}

package com.niit.service.project.controller;

import com.niit.service.project.pojo.SkProjectComments;
import com.niit.service.project.service.SkProjectCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SkProjectCommentsController
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:11
 **/
@CrossOrigin
@RestController
@RequestMapping("/projectcomments")
public class SkProjectCommentsController {

    @Autowired
    private SkProjectCommentsService skProjectCommentsService;

    @GetMapping
    public Integer insertProjectComments(SkProjectComments skProjectComments) {
        Integer insertStatus = 0;
        try {
            insertStatus = skProjectCommentsService.insert(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    @DeleteMapping("/{id}")
    public Integer deleteProjectComments(@PathVariable Integer id) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skProjectCommentsService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    @PutMapping
    public Integer updateProjectComments(SkProjectComments skProjectComments) {
        Integer updateStatus = 0;
        try {
            updateStatus = skProjectCommentsService.updateByPrimaryKey(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    @GetMapping("/{id}")
    public SkProjectComments selectProjectComments(@PathVariable Integer id) {
        SkProjectComments skProjectComments = null;
        try {
            skProjectComments = skProjectCommentsService.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProjectComments;
    }
}

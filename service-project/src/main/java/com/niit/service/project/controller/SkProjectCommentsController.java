package com.niit.service.project.controller;

import com.niit.service.project.pojo.SkProjectComments;
import com.niit.service.project.service.SkProjectCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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

    // insert
    @GetMapping
    public Integer insertProjectComments(@RequestBody SkProjectComments skProjectComments) {
        Integer insertStatus = 0;
        try {
            insertStatus = skProjectCommentsService.insert(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    // delete
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

    // update
    @PutMapping
    public Integer updateProjectComments(@RequestBody SkProjectComments skProjectComments) {
        Integer updateStatus = 0;
        try {
            updateStatus = skProjectCommentsService.updateByPrimaryKey(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    // select
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

    // 批量审核作品留言
    @PutMapping("/more")
    public Integer updateMoreProjectComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        Integer updateStatus = 0;
        try {
            updateStatus = skProjectCommentsService.updateMoreProjectComment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    // 批量删除作品留言
    @DeleteMapping("/more")
    public Integer deleteMoreProjectComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        Integer deleteStatus = 0;
        try {
            deleteStatus = skProjectCommentsService.deleteMoreProjectComment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }
}

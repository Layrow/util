package com.niit.website.cms.controller;

import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkProjectComments;
import com.niit.website.cms.service.SkProjectCommentsService;
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
    public void insertProjectComments(@RequestBody SkProjectComments skProjectComments) {
        try {
            skProjectCommentsService.insert(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public void deleteProjectComments(@PathVariable Integer id) {
        try {
            skProjectCommentsService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update
    @PutMapping
    public void updateProjectComments(@RequestBody SkProjectComments skProjectComments) {
        try {
            skProjectCommentsService.updateByPrimaryKey(skProjectComments);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void updateMoreProjectComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            skProjectCommentsService.updateMoreProjectComment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 批量删除作品留言
    @DeleteMapping("/more")
    public void deleteMoreProjectComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            skProjectCommentsService.deleteMoreProjectComment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询所有作品留言
    @GetMapping("/more")
    public PageInfo<SkProjectComments> selectAllProjectComments(@RequestParam(required = false,defaultValue = "") Integer projectId, Integer currentPage, Integer pageSize) {
        PageInfo<SkProjectComments> pageInfo = null;
        try {
            pageInfo = skProjectCommentsService.selectAllProjectComment(projectId,currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}

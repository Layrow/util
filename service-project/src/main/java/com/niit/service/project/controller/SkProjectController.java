package com.niit.service.project.controller;

import com.github.pagehelper.PageInfo;
import com.niit.service.project.pojo.SkProject;
import com.niit.service.project.service.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SkProjectController
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:11
 **/
@CrossOrigin
@RestController
@RequestMapping("/project")
public class SkProjectController {

    @Autowired
    private SkProjectService skProjectService;

    // insert
    @PostMapping
    public Integer insertProject(SkProject skProject) {
        Integer insertStatus = 0;
        try {
            insertStatus = skProjectService.insert(skProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    // delete
    @DeleteMapping("/{id}")
    public Integer deleteProject(@PathVariable Integer id) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skProjectService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    // select
    @GetMapping("/{id}")
    public SkProject selectProject(@PathVariable Integer id) {
        SkProject skProject = null;
        try {
            skProject = skProjectService.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProject;
    }

    // update
    @PutMapping
    public Integer updateProject(SkProject skProject) {
        Integer updateStatus = 0;
        try {
            updateStatus = skProjectService.updateByPrimaryKey(skProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    // delete more
    @DeleteMapping
    public Integer deleteMoreProject(HttpServletRequest request) {
        Integer deleteMoreStatus = 0;
        String id = request.getParameter("id");
        try {
            deleteMoreStatus = skProjectService.deleteMoreProject(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteMoreStatus;
    }

    // select more
    @GetMapping("/more")
    public PageInfo<SkProject> selectMoreProject(@RequestParam(defaultValue = "") Integer status, @RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        try {
            pageInfo = skProjectService.selectAllProject(status,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    // 批量审核作品
    @PutMapping("/more")
    public Integer updateMoreProject(HttpServletRequest request) {
        Integer updateStatus = 0;
        String id = request.getParameter("id");
        try {
            updateStatus = skProjectService.updateMoreProject(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    // 根据title模糊查询作品
    @GetMapping("/title")
    public PageInfo<SkProject> likeSelectProjectByTitle(@RequestParam(defaultValue = "") String status,@RequestParam String title,@RequestParam Integer currentPage,
                                                        @RequestParam Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        try {
            pageInfo = skProjectService.likeSelectProjectByTitle(status,title,currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
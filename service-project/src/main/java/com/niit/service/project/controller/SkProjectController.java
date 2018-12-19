package com.niit.service.project.controller;

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

}

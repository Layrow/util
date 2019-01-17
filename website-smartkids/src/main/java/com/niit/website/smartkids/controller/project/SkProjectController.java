package com.niit.website.smartkids.controller.project;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.niit.website.smartkids.service.projectservice.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public void insertProject(@RequestBody SkProject skProject) {
        try {
            skProjectService.insertProject(skProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete
    @DeleteMapping("/delete")
    public void deleteProject(Integer id) {
        try {
            skProjectService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // select
    @GetMapping("/{id}")
    public String selectProject(@PathVariable Integer id) {
        String skProject = null;
        try {
            skProject = skProjectService.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProject;
    }

    // update
    @PutMapping
    public void updateProject(@RequestBody SkProject skProject,@RequestParam(value = "operationUserId",required = false) Integer operationUserId,
                              @RequestParam(value = "operationUserName",required = false) String operationUserName,
                              @RequestParam(value = "operate",required = false,defaultValue = "other") String operate) {
        try {
            skProjectService.updateByPrimaryKey(skProject,operationUserId,operationUserName,operate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete more
    @DeleteMapping
    public void deleteMoreProject(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            skProjectService.deleteMoreProject(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void updateMoreProject(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            skProjectService.updateMoreProject(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // 按照置顶/推荐/点赞数 查询
    @GetMapping("/type")
    public PageInfo<SkProject> likeSelectProjectAll(@RequestParam(defaultValue = "",required = false) String title,
                                                    @RequestParam(defaultValue = "1",required = false) Integer status,
                                                    @RequestParam(defaultValue = "",required = false) Integer categoryId,
                                                    @RequestParam(defaultValue ="")String orderBy,
                                                    @RequestParam Integer currentPage,
                                                    @RequestParam Integer pageSize) {
        PageInfo<SkProject> pageInfo = null;
        try {
            pageInfo = skProjectService.likeSelectProjectAll(title,status,categoryId,orderBy, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    // 查询用户作品
    @GetMapping("/user")
    public String selectProjectByUserId(Integer userId) {
        return skProjectService.selectProjectByUserId(userId);
    }

    @GetMapping("/user_operation")
    public List selectProjectOperation(Integer userId, Integer projectId) {
        return skProjectService.selectProjectOperation(userId,projectId);
    }
}

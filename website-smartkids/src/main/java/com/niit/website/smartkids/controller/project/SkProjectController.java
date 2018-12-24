package com.niit.website.smartkids.controller.project;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.project.SkProject;
import com.niit.website.smartkids.service.projectservice.SkProjectService;
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
    public void insertProject(@RequestBody SkProject skProject) {
        try {
            skProjectService.insert(skProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete
    @DeleteMapping("/delete")
    public void deleteProject(Integer id,Integer userId,String useName) {
        // TODO 修改传递参数的方式
        try {
            skProjectService.deleteByPrimaryKey(id,userId,useName);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void updateProject(@RequestBody SkProject skProject) {
        try {
            skProjectService.updateByPrimaryKey(skProject);
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
    public PageInfo<SkProject> selectProject(Integer userId,Integer currentPage,Integer pageSize) {
        return skProjectService.selectProjectByUserId(userId, currentPage, pageSize);
    }
}

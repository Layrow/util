package com.niit.service.project.controller;

import com.github.pagehelper.PageInfo;
import com.niit.service.project.pojo.SkProject;
import com.niit.service.project.service.SkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public String insertProject(@RequestBody SkProject skProject) {
        skProject.setFavCount(0);
        skProject.setIsRecommnd(0);
        skProject.setViewCount(0);
        skProject.setIsTop(0);
        skProject.setLikesCount(0);
        skProject.setStatus(1);
        skProject.setUpdatetime(new Date());
        // 自增主键
        return skProjectService.insert(skProject);
    }

    // delete
    @DeleteMapping("/delete")
    public Integer deleteProject(Integer id) {
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
    public String selectProject(@PathVariable Integer id) {
        String skProject = null;
        try {
            skProject = skProjectService.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProject;
    }

    // 只包含单纯的作品对象
    @GetMapping("/info/{id}")
    public SkProject selectProjectInfo(@PathVariable Integer id) {
        SkProject skProject = null;
        try {
            skProject = skProjectService.selectProjectInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProject;
    }

    // update
    @PutMapping
    public Integer updateProject(@RequestBody SkProject skProject) {
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

    // 批量操作作品（置顶，推荐，审核）
    @PutMapping("/more")
    public Integer updateMoreProject(HttpServletRequest request) {
        Integer updateStatus = 0;
        String id = request.getParameter("id");
        String sign = request.getParameter("sign");
        try {
            updateStatus = skProjectService.updateMoreProject(sign,id);
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

    // 按照置顶/推荐/点赞数 查询
    @GetMapping("/type")
    public PageInfo<SkProject> likeSelectProjectAll(@RequestParam(defaultValue = "",required = false) String title,
                                                    @RequestParam(defaultValue = "",required = false) Integer status,
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
    public List selectProjectOperation(@RequestParam Integer user_id,@RequestParam Integer project_id) {
        return skProjectService.selectProjectOperation(user_id, project_id);
    }
}

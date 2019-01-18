package com.niit.service.bbs.controller;

import com.github.pagehelper.PageInfo;
import com.niit.service.bbs.pojo.SkBbsSection;
import com.niit.service.bbs.service.SkBbsSectionService;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/4
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/section")
public class SkBbsSectionController {

    @Resource
    SkBbsSectionService skBbsSectionService;

    @GetMapping("/getName")
    public String getName(@RequestParam Integer sectionId) {
        return skBbsSectionService.selectName(sectionId);
    }

    /**
     * 添加 模块功能
     *
     * @param section
     * @return 成功返回true
     */
    @PostMapping("/add")
    public boolean insertSection(@RequestBody SkBbsSection section) {
        return skBbsSectionService.addSection(section);
    }

    /**
     * 删除模块操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public boolean deleteSection(@RequestParam String id) {
        return skBbsSectionService.deleteSection(id);
    }


    /**
     * 修改所有的 模块排序值
     *
     * @param list
     * @return
     */
    @PutMapping("/edit")
    public boolean editOrder(@RequestBody List<SkBbsSection> list) {
        return skBbsSectionService.updateOderBatch(list);
    }

    /**
     * 查询所有栏目类别,根据排序值进行排列
     *
     * @return
     */
    @GetMapping("/list")
    public List<SkBbsSection> showAllSection() {
        return skBbsSectionService.selectAllSection();
    }

    /**
     * 修改单个栏目信息
     *
     * @param section
     * @return
     */
    @PutMapping("/editOne")
    public boolean editOne(@RequestBody SkBbsSection section) {
        return skBbsSectionService.editSection(section);
    }

    /**
     * 分页模糊查询栏目
     *
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("like")
    public PageInfo<SkBbsSection> like(Integer currentPage, Integer pageSize, @RequestParam String name) {
        return skBbsSectionService.likeSelectAll(currentPage, pageSize, name);
    }
}

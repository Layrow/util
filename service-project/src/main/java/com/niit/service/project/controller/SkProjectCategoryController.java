package com.niit.service.project.controller;

import com.niit.service.project.pojo.SkProjectCategory;
import com.niit.service.project.service.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SkProjectCategoryController
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 16:11
 **/
@CrossOrigin
@RestController
@RequestMapping("/projectcategory")
public class SkProjectCategoryController {

    @Autowired
    private SkProjectCategoryService skProjectCategoryService;

    @PostMapping
    public Integer insert(@RequestBody SkProjectCategory skProjectCategory) {
        Integer insertStatus = 0;
        try {
            insertStatus = skProjectCategoryService.insert(skProjectCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    @DeleteMapping("/{id}")
    public Integer deleteProjectCategory(@PathVariable  Integer id) {
        Integer deleteStatus = 0;
        try {
            deleteStatus = skProjectCategoryService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

    @GetMapping("/{id}")
    public SkProjectCategory selectProjectCategory(@PathVariable Integer id) {
        SkProjectCategory skProjectCategory = null;
        try {
            skProjectCategory = skProjectCategoryService.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProjectCategory;
    }

    @PutMapping
    public Integer updateProjectCategory(@RequestBody SkProjectCategory skProjectCategory) {
        Integer updateStatus = 0;
        try {
            updateStatus = skProjectCategoryService.updateByPrimaryKey(skProjectCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
}

package com.niit.website.smartkids.controller.project;

import com.niit.website.smartkids.pojo.project.SkProjectCategory;
import com.niit.website.smartkids.service.projectservice.SkProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void insert(@RequestBody SkProjectCategory skProjectCategory) {
        try {
            skProjectCategoryService.insert(skProjectCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProjectCategory(@PathVariable Integer id) {
        try {
            skProjectCategoryService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void updateProjectCategory(@RequestBody SkProjectCategory skProjectCategory) {
        try {
            skProjectCategoryService.updateByPrimaryKey(skProjectCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询所有作品类别
    @GetMapping("/all")
    public List<SkProjectCategory> selectAllProjectCategory() {
        List<SkProjectCategory> skProjectCategoryList = null;
        try {
            skProjectCategoryList = skProjectCategoryService.selectAllProjectCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skProjectCategoryList;
    }
}

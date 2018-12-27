package com.niit.website.lms.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.website.lms.pojo.SkLmsStudentsCn;
import com.niit.website.lms.service.SkLmsStudentsCnService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/11/13
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/batch/students")
public class SkLmsStudentsCnController {
    @Resource
    SkLmsStudentsCnService studentsService;

    /**
     * 把指定班级的学生信息导出到Excel
     * @param batchId
     * @return
     */
    @PostMapping("/export")
    public boolean export(@RequestParam Integer batchId){
        return  studentsService.export(batchId);
    }

    @PostMapping("importExcel")
    public boolean importExcel(@RequestParam MultipartFile excel, @RequestParam Integer batchId, @RequestParam String className) {
        try {
            InputStream inputStream=excel.getInputStream();
            List<Object> list=EasyExcelFactory.read(inputStream,new Sheet(1,1,SkLmsStudentsCn.class));
            Iterator<Object> iterator=list.iterator();
            List<SkLmsStudentsCn> students=new ArrayList<>();
            while (iterator.hasNext()){
                students.add((SkLmsStudentsCn) iterator.next());
            }
            studentsService.imExcel(students,batchId,className);
            inputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
        @PostMapping("/addOne")
    public boolean insert(@RequestBody SkLmsStudentsCn studentsCn, @RequestParam Integer batchId, @RequestParam String className){
        return  studentsService.addOne(studentsCn,batchId,className);
    }
    @PutMapping("/editPW")
    public boolean updatePW(@RequestParam Integer id){
        return  studentsService.reSetPassword(id);
    }
    @GetMapping("/editPre")
    public SkLmsStudentsCn editPre(@RequestParam Integer id){
        return studentsService.editStudentInfoPre(id);
    }
    @PutMapping("/edit")
    public boolean edit(@RequestBody SkLmsStudentsCn studentsCn){
        return studentsService.editStudentInfo(studentsCn);
    }
    @DeleteMapping("/delete")
    public  boolean delete (@RequestParam Integer id){
        return studentsService.deleteStudentByID(id);
    }

    @GetMapping("/showAllStudents")
    public Map<String, Object> showStudents(Integer batchId){
        return this.getStudnetsInfo(batchId);
    }

    /**
     * 取得班级学生信息,班级学生对象和班级学生数量
     * @param batchId
     * @return
     */
    public   Map<String, Object> getStudnetsInfo(Integer batchId){
        Map<String, Object> totalMap = null;
        try {
            String totalString = studentsService.showStudents(batchId);
            //把String转成Map
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (totalString==null||totalString.isEmpty()){
                return totalMap;
            }
            totalMap = gson.fromJson(totalString, type);
        } catch (Exception e) {
            e.printStackTrace();
            return totalMap;
        }
        return totalMap;
    }


    @GetMapping("/splitShowAll")
    public PageInfo<SkLmsStudentsCn> splitShowAll(@RequestParam Integer batchId,@RequestParam  Integer currentPage,@RequestParam Integer pageSize){
        return studentsService.splitAllStudents(batchId,currentPage,pageSize);
    }

}

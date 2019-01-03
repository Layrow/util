package com.niit.service.lms.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.service.lms.pojo.SkLmsStudentsCn;
import com.niit.service.lms.service.SkLmsStudentsCnService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/11/8
 * @since 1.0.0
 */

@RestController
@CrossOrigin
@RequestMapping("/batch/students")
public class SkLmsStudentsCnController {
    @Resource
    SkLmsStudentsCnService studentsService;

    /**
     * 单个学生的添加
     * @param studentsCn 学生对象
     * @param batchId 学生所在班级
     * @return
     */
    @PostMapping("/addOne")
    public boolean insert(@RequestBody SkLmsStudentsCn studentsCn, @RequestParam Integer batchId, @RequestParam String className )  {
        return  studentsService.addOne(studentsCn,batchId,className);
    }

    @PostMapping("importExcel")
    public boolean importExcel(@RequestBody List<SkLmsStudentsCn> list,@RequestParam Integer batchId,@RequestParam String className){
        try {
           studentsService.exportExcel(list,className,batchId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 学生密码修改
     * @param id 学生ID
     * @return 修改成功返回 true
     */
    @PutMapping("/editPW")
    public boolean updatePW(@RequestParam Integer id){
        return  studentsService.reSetPassword(id);
    }

    /**
     * 实现学生 数据修改前的回显
     * @param id 需要修改学生的数据id
     * @return 学生对象
     */
    @GetMapping("/editPre")
    public SkLmsStudentsCn editPre(@RequestParam Integer id){
        return studentsService.editStudentInfoPre(id);
    }

    /**
     * 进行学生对象信息的修改
     * @param studentsCn
     * @return
     */
    @PutMapping("/edit")
    public boolean edit(@RequestBody SkLmsStudentsCn studentsCn){
        return studentsService.editStudentInfo(studentsCn);
    }

    /**
     * 学生对象的删除,以及级联表的删除
     * @param id 需要删除的学生id
     * @return
     */
    @DeleteMapping("/delete")
    public  boolean delete (@RequestParam Integer id){
        return studentsService.deleteStudentByID(id);
    }

    @PostMapping("export")
    public String export( @RequestParam Integer batchId){
       try {
           OutputStream out = new FileOutputStream("C:\\");
           ExcelWriter writer = EasyExcelFactory.getWriter(out);
           Sheet sheet2 = new Sheet(1, 0, SkLmsStudentsCn.class);
           sheet2.setSheetName("学生");
           writer.write(studentsService.getAllStudents(batchId), sheet2);
           writer.finish();
           out.close();
           return "true";
       }catch (Exception e){
           e.printStackTrace();
           return "false";
       }
    }

    /**
     * 给客户端展示该班级下的所有学生,并返回学生数量
     *
     * @param batchID 班级id
     * @return map以String类型保存
     */
    @GetMapping("/showAllStudents")
    public String showAllStudents(@RequestParam Integer batchID){
        return studentsService.showStudents(batchID);
    }

    /**
     * 分页查询所有学生
     * @param batchId 学生班级ID
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return
     */
    @GetMapping("splitShowAll")
    public PageInfo<SkLmsStudentsCn> showAllSplit(@RequestParam Integer batchId,@RequestParam Integer currentPage,@RequestParam Integer pageSize){
        PageInfo<SkLmsStudentsCn> students =null;
        try {
            students=studentsService.splitShowStudnets(batchId,currentPage,pageSize);
            return  students;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  students;
    }

}

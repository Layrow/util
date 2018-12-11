package com.niit.service.lms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.pojo.SkLmsBatchCoursewareCn;
import com.niit.service.lms.service.SkLmsBatchCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 14:32
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/batchCourse")
public class SkLmsBatchCoursewareCnController {
    @Autowired
    private SkLmsBatchCoursewareCnService ccs;

    /**
     * 功能描述:删除课件 单个
     *
     * @return java.lang.String
     * @author huangwei
     * @date 2018/11/8
     * @params [id]
     */
    @DeleteMapping
        public String deleteByPrimaryKeyInfo(Integer id) {
        int status = 0;
            status  = ccs.deleteByPrimaryKey(id);
        if (status > 0) {
            return "删除成功";
        } else
            return "删除失败";
    }
    /**
    * 功能描述: 添加（共享）
    * @author huangwei
    * @date :2018/11/12
    * @params [record]
    * @return int
    */
    @PostMapping
    public int insertSelectiveInfo(@RequestBody SkLmsBatchCoursewareCn record) {
        int i = ccs.insertSelective(record);
        return i;
    }
    /**
    * 功能描述:查询全部班级（包括共享的班级）
    * @author huangwei
    * @date :2018/11/20
    * @params [coursewareId, facultyId]
    * @return java.lang.String
    */
    @GetMapping("/select")
    public  String  select( Integer coursewareId, Integer facultyId){
        List<Map<String, Object>> select = ccs.select(coursewareId,facultyId);
        //return  select;
        //转换成string格式
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(select);
    }
    /**
    * 功能描述:取消分享（删除班级课件数据）
    * @author huangwei
    * @date :2018/11/20
    * @params [request, coursewareId]
    * @return java.lang.String
    */
    @DeleteMapping("/many")
    public String deleteAd(HttpServletRequest request,Integer coursewareId) {
        String id = request.getParameter("batch_id");
        ccs.deleteAd(id,coursewareId);
        return "批量删除";
    }

    @PostMapping("/batch")
    public  String  insertBatchInfo(@RequestBody List<SkLmsBatchCoursewareCn> sk){
     //  System.out.println("+++++++++++++++"+sk);
        int i = ccs.insertBatch(sk);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(i);



    }


}

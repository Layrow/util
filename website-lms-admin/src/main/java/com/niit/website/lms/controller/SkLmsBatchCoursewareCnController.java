package com.niit.website.lms.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niit.website.lms.pojo.SkLmsBatchCoursewareCn;
import com.niit.website.lms.service.SkLmsBatchCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 15:22
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/batchCourse")
public class SkLmsBatchCoursewareCnController {
    @Autowired
    private SkLmsBatchCoursewareCnService ccs;

/**
* 功能描述:
* @author huangwei
* @date 2018/11/11 0011
* @params [id]
* @return int
*/
    @DeleteMapping
    public int deleteByPrimaryKeyInfo(Integer id){
        int i = ccs.deleteByPrimaryKey(id);
       return  i;
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
     * 功能描述:查询所有班级进行分享
     * @author huangwei
     * @date :2018/11/14
     * @params []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @GetMapping("/select")
    public  List<Map<String, Object>>  select( Integer coursewareId, Integer facultyId){

        List<Map<String, Object>>  list = null;
        String select = ccs.select(coursewareId, facultyId);
        Type type = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        Gson gson = new Gson();
        if(select==null||select.isEmpty()){
            return  list;
        }
        list = gson.fromJson(select,type);
        return  list;
    }
    /**
    * 功能描述:批量删除
    * @author huangwei
    * @date :2018/11/20
    * @params [request, coursewareId]
    * @return java.lang.String
    */
    @DeleteMapping("/many")
    public String deleteAd(HttpServletRequest request, Integer coursewareId) {
        String id = request.getParameter("batch_id");
        ccs.deleteAd(id,coursewareId);
        return "批量删除";
    }
     /**
     * 功能描述: 批量添加
     * @author huangwei
     * @date :2018/11/22
     * @params [sk]
     * @return void
     */
    @PostMapping("/batch")
    public  void  insertBatchInfo(@RequestBody List<SkLmsBatchCoursewareCn> sk){
        ccs.insertBatch(sk);
    }


}

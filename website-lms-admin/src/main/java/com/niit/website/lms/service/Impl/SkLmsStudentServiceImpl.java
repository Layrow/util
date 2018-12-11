package com.niit.website.lms.service.Impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.lms.pojo.SkLmsCoursewareCn;
import com.niit.website.lms.pojo.SkLmsHomeworkCn;
import com.niit.website.lms.service.SkLmsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.09 09:46
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.09 09:46
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@Service
public class SkLmsStudentServiceImpl implements SkLmsStudentService {


    final String SERVICE_NAME = "service-lms";

    @Autowired
    RestTemplate restTemplate;

    //查找某班作业要求列表
    @Override
    public List<SkLmsHomeworkCn> selectHomeworkByBatch(int batch_id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/student/homework/{batch_id}", List.class, batch_id);
    }

    //查找某班课件列表
    @Override
    public PageInfo<SkLmsCoursewareCn> selectCoursewareByBatch(int batch_id,Integer currentPage,Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/student/courseware/{batch_id}?currentPage="+currentPage+"&pageSize="+pageSize,PageInfo.class,batch_id);
    }

    //查找单项作业要求
    @Override
    public SkLmsHomeworkCn selectHomeworkById(int homework_id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/student/homework/*/{homework_id}", SkLmsHomeworkCn.class, homework_id);
    }

    //提交学生作业
    @Override
    public Integer insertStudentHomework(String json) {
        try {
            restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            restTemplate.postForObject("http://" + SERVICE_NAME + "/student/homework/*/*", json, String.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //查看学生作业
    @Override
    public String selectHomeworkAnswerById(int homework_id, int student_id) {
//        final ParameterizedTypeReference<Map<String,Object>> typeRef = new ParameterizedTypeReference<Map<String,Object>>() {};
//        ResponseEntity<Map<String,Object>> responseEntity =
//        restTemplate.exchange("http://" + SERVICE_NAME + "/student/homework/*/{homework_id}/{student_id}", HttpMethod.GET, null,typeRef,homework_id,student_id);
//        Map<String,Object> totalMap = responseEntity.getBody();
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/student/homework/*/{homework_id}/{student_id}",String.class,homework_id,student_id);

    }

    //编辑学生作业
    @Override
    public Integer updateHomeworkAnswer(String json) {
        try {
            restTemplate.put("http://" + SERVICE_NAME + "/student/homework/*/*", json);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}

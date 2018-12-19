package com.niit.website.lms.service.Impl;

import com.niit.website.lms.pojo.SkLmsHomeworkCn;
import com.niit.website.lms.service.SkLmsHomeworkCnService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SkLmsHomeworkCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 15:34
 **/
@Service
public class SkLmsHomeworkCnServiceImpl implements SkLmsHomeworkCnService {

    final String SERVICE_NAME = "service-lms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void insertHomework(String json) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = new JSONObject(json);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        restTemplate.postForEntity("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework", formEntity, String.class);

    }

    @Override
    public void updateHomework(String json) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = new JSONObject(json);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        restTemplate.put("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework",formEntity);
    }

    @Override
    public void deleteHomework(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/{id}",id);
    }


    @Override
    public List<SkLmsHomeworkCn> selectHomeworkByFacultyId(Integer facultyId) {
        // getForObject是不会知道List<>中传递的数据类型的，会被默认为LinkedHashMap，最后变成List<LinkedHashMap>
        ParameterizedTypeReference<List<SkLmsHomeworkCn>> typeRef = new ParameterizedTypeReference<List<SkLmsHomeworkCn>>() {
        };
        ResponseEntity<List<SkLmsHomeworkCn>> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/faculty/" +facultyId, HttpMethod.GET, null, typeRef);
        List<SkLmsHomeworkCn> skLmsHomeworkCnList = responseEntity.getBody();
        return skLmsHomeworkCnList;
    }

    @Override
    public String selectByPrimaryKey(Integer id) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/" +id, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();

    }

    @Override
    public String selectStudentByHomeworkId(Integer batchId,Integer homeworkId,Integer currentPage,Integer pageSize) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/stu?batchId="
                        + batchId+"&homeworkId="+homeworkId+"&currentPage="+currentPage+"&pageSize="+pageSize
                , HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();

    }

    @Override
    public String selectHomeworkByBatchId(Integer batchId) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/batch/" + batchId, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    @Override
    public String selectScore(Integer batchId, Integer homeworkId) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkCn/homework/score?batchId=" + batchId + "&homeworkId=" + homeworkId, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }
}

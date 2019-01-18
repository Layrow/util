package com.niit.website.lms.service.Impl;


import com.niit.website.lms.pojo.SkLmsHomeworkAnswerCn;
import com.niit.website.lms.service.SkLmsHomeworkAnswerCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SkLmsHomeworkAnswerCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 10:38
 **/
@Service
public class SkLmsHomeworkAnswerCnServiceImpl implements SkLmsHomeworkAnswerCnService {

    final String SERVICE_NAME = "service-lms";
    @Autowired
    RestTemplate restTemplate;


    @Override
    public String selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkAnswerCn/homeworkanswer/" + studentId + "/" + homeworkId, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    @Override
    public void updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record) {
        restTemplate.put("http://" + SERVICE_NAME + "/skLmsHomeworkAnswerCn/homeworkanswer/", record);
    }

    @Override
    public String selectStudentHomeworkInfo(Integer batchId, Integer studentId) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkAnswerCn/analyse?batchId=" + batchId + "&studentId=" + studentId, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    @Override
    public String selectBacthInfo(Integer batchId) {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skLmsHomeworkAnswerCn/analyse/batch?batchId=" + batchId, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }
}

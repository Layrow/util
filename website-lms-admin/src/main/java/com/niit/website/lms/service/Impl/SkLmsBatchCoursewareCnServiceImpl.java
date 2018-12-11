package com.niit.website.lms.service.Impl;

import com.niit.common.entity.BaseResult;
import com.niit.website.lms.pojo.SkLmsBatchCoursewareCn;
import com.niit.website.lms.service.SkLmsBatchCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @Auther: huangwei
 * @Date: 2018/11/8 14:54
 * @Description:
 */
@Service
public class SkLmsBatchCoursewareCnServiceImpl implements SkLmsBatchCoursewareCnService {
    final String SERVICE_NAME = "service-lms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/batchCourse?id=" + id, id);
        return 1;
    }

    @Override
    public int insertSelective(SkLmsBatchCoursewareCn record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/batchCourse", record, BaseResult.class);
        return 1;
    }

    @Override
    public String select(Integer coursewareId, Integer facultyId) {
       /* ParameterizedTypeReference<List<Map<String, Object>>> reference = new ParameterizedTypeReference<List<Map<String, Object>>>() {
        };
        ResponseEntity<List<Map<String, Object>>> entity = restTemplate.exchange("http://" + SERVICE_NAME + "/batchCourse/select?coursewareId=" + coursewareId + "&facultyId=" + facultyId, HttpMethod.GET, null, reference);

        List<Map<String, Object>> list = entity.getBody();*/

        return restTemplate.getForObject("http://" + SERVICE_NAME + "/batchCourse/select?coursewareId=" + coursewareId + "&facultyId=" + facultyId, String.class);

    }

    @Override
    public void deleteAd(String batch_id, Integer coursewareId) {
        restTemplate.delete("http://" + SERVICE_NAME + "/batchCourse/many?batch_id=" + batch_id + "&coursewareId=" + coursewareId, batch_id, coursewareId);

    }

    @Override
    public String insertBatch(List<SkLmsBatchCoursewareCn> sk) {
      return restTemplate.postForObject("http://" + SERVICE_NAME + "/batchCourse/batch", sk, String.class);

    }

}

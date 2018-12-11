package com.niit.website.lms.service.Impl;

import com.niit.common.entity.BaseResult;
import com.niit.website.lms.pojo.SkLmsBatchCn;
import com.niit.website.lms.service.SkLmsBatchCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName SkLmsBatchServiceImpl
 * @Description batch service
 * @Author liyuhao
 * @Date 2018/11/8 13:26
 **/
@Service
public class SkLmsBatchServiceImpl implements SkLmsBatchCnService {

    final String SERVICE_NAME = "service-lms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/skLmsBatchCn/batch/{id}",id);
        return 1;
    }


    @Override
    public int insertSelective(SkLmsBatchCn record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/skLmsBatchCn/batch",record, BaseResult.class);
        return 1;
    }


    @Override
    public int updateByPrimaryKeySelective(SkLmsBatchCn record) {
        restTemplate.put("http://" + SERVICE_NAME + "/skLmsBatchCn/batch",record);
        return 1;
    }


    @Override
    public List<SkLmsBatchCn> selectByFacultyId(Integer facultyId) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/skLmsBatchCn/batch/{facultyId}",List.class,facultyId);
    }
}

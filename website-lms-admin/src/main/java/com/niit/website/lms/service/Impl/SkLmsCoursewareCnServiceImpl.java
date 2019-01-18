package com.niit.website.lms.service.Impl;

import com.niit.website.lms.service.SkLmsCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: huangwei
 * @Date:2018/11/12 10:45
 * @Description:
 */
@Service
public class SkLmsCoursewareCnServiceImpl implements SkLmsCoursewareCnService {

    final String SERVICE_NAME = "service-lms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/course?id=" + id, id);
        return 1;
    }

    //模糊查询
    @Override
    public String selectAll(Integer facultyId, int currentPage, int pageSize, String courseware_title) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/course?facultyId=" + facultyId + "&currentPage=" + currentPage + "&pageSize=" + pageSize + "&courseware_title=" + courseware_title, String.class);
    }

    @Override
    public Integer insertSelective(String url) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/course", url, String.class);
        return 1;
    }

    @Override
    public String selectAllWare(Integer facultyId, int currentPage, int pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/course/all?facultyId=" + facultyId + "&currentPage=" + currentPage + "&pageSize=" + pageSize, String.class);
    }

    @Override
    public String selectAllBatchWare(Integer batchId, int currentPage, int pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/course/allBatch?batchId=" + batchId + "&currentPage=" + currentPage + "&pageSize=" + pageSize, String.class);
    }

}

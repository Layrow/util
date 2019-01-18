package com.niit.service.lms.service;

import com.niit.service.lms.pojo.SkLmsCoursewareCn;

import java.io.File;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 15:51
 * @Description:
 */
public interface SkLmsCoursewareCnService {
    int deleteByPrimaryKey(Integer id);

    int insertCn(SkLmsCoursewareCn record);

    //模糊查询
    String selectAll(Integer facultyId, int currentPage, int pageSize, String courseware_title);

    //上传文件
    int insertSelective(String url);

    int insert(File file, String fileName,
               boolean thumbMark);

    //查询所有
    String selectAllWare(Integer facultyId, int currentPage, int pageSize);

    //查询班级下的所有课件
    String selectAllBatchWare(Integer batchId, int currentPage, int pageSize);


}

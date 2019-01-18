package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsCoursewareCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkLmsCoursewareCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insertCn(SkLmsCoursewareCn record);

    int insert(SkLmsCoursewareCn record);

    int insertSelective(SkLmsCoursewareCn record);

    List<SkLmsCoursewareCn> selectByCoursewareIdList(List<Integer> coursewareIdList);

    SkLmsCoursewareCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsCoursewareCn record);

    int updateByPrimaryKey(SkLmsCoursewareCn record);

    // List<SkLmsCoursewareCn>  selectAll(String courseware_title);
    //模糊查询
    List<SkLmsCoursewareCn> selectAll(@Param("facultyId") Integer facultyId, @Param("courseware_title") String courseware_title);

    //查询老师我的课件下所有课件
    List<SkLmsCoursewareCn> selectAllWare(Integer facultyId);

    //查询班级下的课件
    List<SkLmsCoursewareCn> selectAllBatchWare(Integer batchId);
}
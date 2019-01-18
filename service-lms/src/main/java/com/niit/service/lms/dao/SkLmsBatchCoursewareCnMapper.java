package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsBatchCoursewareCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkLmsBatchCoursewareCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsBatchCoursewareCn record);

    int insertSelective(SkLmsBatchCoursewareCn record);

    SkLmsBatchCoursewareCn selectByPrimaryKey(Integer id);

    List<Integer> selectByBatchId(Integer batch_id);


    int updateByPrimaryKeySelective(SkLmsBatchCoursewareCn record);

    int updateByPrimaryKey(SkLmsBatchCoursewareCn record);

    List<Map<String, Object>> selectClass();

    //查询所有班级
    List<Map<String, Object>> selectAllBatch(Integer facultyId);

    //查询关联id
    List<Map<String, Object>> selectId(@Param("coursewareId") Integer coursewareId, @Param("facultyId") Integer facultyId);

    //批量删除
    void deleteAd(@Param("list") List<String> list, @Param("coursewareId") Integer coursewareId);

    //分享班级
    List<Map<String, Object>> select(@Param("coursewareId") Integer coursewareId, @Param("facultyId") Integer facultyId);

    int insertBatch(List<SkLmsBatchCoursewareCn> skList);
}
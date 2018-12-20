package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsHomeworkCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SkLmsHomeworkCnMapper {
    int deleteByPrimaryKey(Integer id);
    //查找列表
    List<SkLmsHomeworkCn> selectByHomeworkIdList(List<Integer> homeworkIdList);

    int insert(SkLmsHomeworkCn record);

    int insertSelective(SkLmsHomeworkCn record);

    SkLmsHomeworkCn selectByPrimaryKey(Integer id);

    SkLmsHomeworkCn selectPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsHomeworkCn record);

    int updateByPrimaryKeyWithBLOBs(SkLmsHomeworkCn record);

    int updateByPrimaryKey(SkLmsHomeworkCn record);
    // 特定老师布置的作业
    List<SkLmsHomeworkCn> selectByFacultyId(Integer facultyId);
    // 获得当前最大作业ID
    int selectMaxHomeworkId();
    // 特定老师布置的作业
    List<SkLmsHomeworkCn> selectHomeworkByFacultyId(Integer facultyId);
    // 查询谁提交了那份作业 , Map<String,Object>是由于值可能是Integer和String
    List<LinkedHashMap<String,Object>> selectStudentByHomeworkId(Integer batchId, Integer homeworkId);
    // 查询谁没提交那份作业
    List<LinkedHashMap<String,Object>> selectNotdoStudentByHomeworkId(Integer batchId, Integer homeworkId);
    // 查询特定班级下的所有作业
    List<SkLmsHomeworkCn> selectHomeworkByBatchId(Integer batchId);

    // 查询特定班级下的特定作业下的得分情况
    List<HashMap<String,Object>> selectScore(@Param("batchId") Integer batchId,@Param("homeworkId") Integer homeworkId);
    int selectFinishCountByHomeworkId(Integer batchId,Integer homeworkId);

}
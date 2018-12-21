package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsHomeworkAnswerCn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SkLmsHomeworkAnswerCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsHomeworkAnswerCn record);

    int insertSelective(SkLmsHomeworkAnswerCn record);

    SkLmsHomeworkAnswerCn selectByPrimaryKey(Integer id);

    //依据student_id和homeworkid查找
    SkLmsHomeworkAnswerCn selectByStudentAndHomeworkId(Integer homework_id,Integer student_id);

    int updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record);

    int updateByPrimaryKeyWithBLOBs(SkLmsHomeworkAnswerCn record);

    int updateByPrimaryKey(SkLmsHomeworkAnswerCn record);

    // 查询作业回答的详细信息
    List<LinkedHashMap<String, Object>> selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId);

    // 查看指定学生的作业得分情况
    List<HashMap<String, Object>> selectScoreByStudentId(Integer studentId);

    // 查询指定学生的作业提交情况
    List<HashMap<String, Object>> selectHomeworkSizeByStudentId(Integer batchId, Integer studentId);

    // 班级作业情况分析
    List<HashMap<String, Object>> selectBacthInfo(Integer batchId);
}
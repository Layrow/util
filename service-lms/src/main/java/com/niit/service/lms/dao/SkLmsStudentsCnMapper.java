package com.niit.service.lms.dao;

import com.niit.service.lms.pojo.SkLmsStudentsCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkLmsStudentsCnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkLmsStudentsCn record);

    int insertSelective(SkLmsStudentsCn record);

    SkLmsStudentsCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLmsStudentsCn record);

    int updateByPrimaryKey(SkLmsStudentsCn record);

    /**
     * 自己添加的方法,根据学生学号来查询学生,用来排除学生重复冗余
     * @param id
     * @return
     */
    SkLmsStudentsCn selectByStudentID(String id);

    /**
     * 查询当前班级下的所有学生
     * @param batchID 当前班级编号.
     * @return 当前的班级
     */
     List<SkLmsStudentsCn> selectAllStudentsByBatch(Integer batchID);
    /**
     * 查询当前班级下的所有学生数量
     * @param batchID 当前班级编号.
     * @return 当前的班级
     */
     int selectAllStudentsByBatchCount(Integer batchID);

    /**
     * 分页查询当前班级下的所有学生
     * @param batchID
     * @return
     */
     List<SkLmsStudentsCn> splitSelectAllStudents(Integer batchID);

    /**
     * 批量添加学生信息
     * @param studentsCnList
     * @return
     */
    int insertExcel(List<SkLmsStudentsCn>studentsCnList );

}
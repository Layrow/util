package com.niit.service.lms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.lms.pojo.SkLmsStudentsCn;

import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/11/8
 * @since 1.0.0
 */
public interface SkLmsStudentsCnService {
    /**
     * 实现单个添加学生的功能
     * 需要调用的方法有
     * 实现学生的添加
     * SkLmsStudentsCnMapper.insertSelective(SkLmsStudentsCn record)
     * 实现班级和学生的关联
     * SkLmsBatchStudentCnMapper.insertSelective(SkLmsBatchStudentCn record);
     *
     * @param student   需要添加的学生对象
     * @param batchID   和学生相关的班级
     * @param ClassName 所在的班级名
     * @return
     */
    boolean addOne(SkLmsStudentsCn student, Integer batchID, String ClassName);

    /**
     * 实现学生密码的重置,使用自动生成的密码
     *
     * @param id 需要修改的学生ID
     * @return
     */
    boolean reSetPassword(int id);

    /**
     * 进行学生信息修改前的 信息回填,实际上是一个查询
     *
     * @param id 需要修改的学生id
     * @return
     */
    SkLmsStudentsCn editStudentInfoPre(int id);

    /**
     * 实现学生信息的修改功能
     *
     * @return 返回学生修改后的信息
     */
    boolean editStudentInfo(SkLmsStudentsCn student);

    /**
     * 根据学生ID删除 学生,并删除he班级关联
     *
     * @param id 学生id
     * @return 成功返回 true 失败返回 false
     */
    boolean deleteStudentByID(Integer id);


    /**
     * 显示当前班级的所有学生
     *
     * @param batchID 当前班级ID
     * @return
     */
    String showStudents(Integer batchID);

    /**
     * 导出班级的所有的学生
     *
     * @param batchID
     * @return
     */
    List<SkLmsStudentsCn> getAllStudents(Integer batchID);

    /**
     * 分页查询学生
     *
     * @param batchId     学生所在班级
     * @param currentPage 当前页,即想要的页数
     * @param pageSize    页面大小 想要一页显示多少学生
     * @return 返回 由分页组件处理
     */
    PageInfo<SkLmsStudentsCn> splitShowStudnets(Integer batchId, Integer currentPage, Integer pageSize);

    /**
     * 解析Excel,插入数据向数据库中
     *
     * @param list
     * @param className
     * @param batchID
     * @return
     */
    boolean exportExcel(List<SkLmsStudentsCn> list, String className, Integer batchID);

}

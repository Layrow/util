package com.niit.website.lms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.lms.pojo.SkLmsStudentsCn;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 实现对学生管理模块的功能<br>
 * 1, 实现单个添加学生<br>
 * 2,实现批量添加学生必须是以.xlsx结尾文档<br>
 * 3,重置密码功能<br>
 * 4,修改学生信息,功能,修改学生姓名和学号<br>
 * 5,删除班级学生功能<br>
 *
 * @author 冯永辉
 * @create 2018/11/8
 * @since 1.0.0
 */
public interface SkLmsStudentsCnService {
    /**
     * 导出班级下的所有学生的信息
     *
     * @param batchId 班级编号
     * @return
     */
    boolean export(Integer batchId);

    boolean imExcel(List<SkLmsStudentsCn> list, Integer batchId, String className);

    /**
     * 实现单个添加学生的功能
     *
     * @param student
     * @return
     */
    boolean addOne(SkLmsStudentsCn student, Integer batchId, String className);

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
     * 实现学生信息修改功能
     *
     * @param student 传入的学生信息的json对象
     * @return 成功返回true
     */
    boolean editStudentInfo(SkLmsStudentsCn student);

    /**
     * 根据学生ID删除 学生
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
     * 分页查询 所有
     *
     * @param batchId     班级ID
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    PageInfo<SkLmsStudentsCn> splitAllStudents(Integer batchId, Integer currentPage, Integer pageSize);
}

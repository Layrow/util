package com.niit.website.lms.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.lms.pojo.SkLmsCoursewareCn;
import com.niit.website.lms.pojo.SkLmsHomeworkCn;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.09 09:45
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.09 09:45
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface SkLmsStudentService {
    //查找某班作业要求列表
    List<SkLmsHomeworkCn> selectHomeworkByBatch(int batch_id);
    //查找某班课件列表
    PageInfo<SkLmsCoursewareCn> selectCoursewareByBatch(int batch_id, Integer currentPage, Integer pageSize);

    //查找单项作业要求
    SkLmsHomeworkCn selectHomeworkById(int homework_id);
    //提交学生作业
    Integer insertStudentHomework(String json);
    //查看学生作业
    String selectHomeworkAnswerById(int homework_id, int student_id);
    //编辑学生作业
    Integer updateHomeworkAnswer(String json);

}

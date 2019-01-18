package com.niit.website.lms.service.Impl;


import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.website.lms.pojo.SkLmsStudentsCn;
import com.niit.website.lms.service.SkLmsStudentsCnService;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;


/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/11/8
 * @since 1.0.0
 */
@Service
public class SkLmsStudentsCnServiceImpl implements SkLmsStudentsCnService {

    final String SERVICE_NAME = "service-lms";
    @Resource
    RestTemplate restTemplate;

    /**
     * 导出班级下的所有学生的信息
     *
     * @param batchId 班级编号
     * @return
     */
    @Override
    public boolean export(Integer batchId) {
        try {
            restTemplate.postForObject("http://"
                    + SERVICE_NAME + "/batch/students/export?batchId=" + batchId, null, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean imExcel(List<SkLmsStudentsCn> list, Integer batchId, String className) {
        try {
            restTemplate.postForObject("http://" + SERVICE_NAME +
                    "/batch/students/importExcel?batchId=" + batchId + "&className=" + className, list, String.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 实现单个添加学生的功能
     *
     * @param student 添加的学生信息
     * @return 添加成功返回 true 失败返回 false
     */
    @Override
    public boolean addOne(SkLmsStudentsCn student, Integer batchId, String className) {
        String result = restTemplate.postForObject("http://" + SERVICE_NAME +
                "/batch/students/addOne?batchId=" + batchId + "&className=" + className, student, String.class);

        if (Tools.isEmpty(result)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 实现学生密码的重置,使用自动生成的密码
     *
     * @param id 需要修改的学生ID
     * @return
     */
    @Override
    public boolean reSetPassword(int id) {
        try {
            restTemplate.put("http://" + SERVICE_NAME + "/batch/students/editPW?id=" + id, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 数据修改前的一个回填显示
     *
     * @param id 需要修改的学生id
     * @return
     */
    @Override
    public SkLmsStudentsCn editStudentInfoPre(int id) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/batch/students/editPre?id=" + id, SkLmsStudentsCn.class);

    }

    /**
     * 实现学生信息的修改功能
     *
     * @param student
     * @return 返回学生修改后的信息
     */
    @Override
    public boolean editStudentInfo(SkLmsStudentsCn student) {
        try {
            restTemplate.put("http://" + SERVICE_NAME + "/batch/students/edit", student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据学生ID删除 学生
     *
     * @param id 学生id
     * @return 成功返回 true 失败返回 false
     */
    @Override
    public boolean deleteStudentByID(Integer id) {
        try {
            restTemplate.delete("http://" + SERVICE_NAME + "/batch/students/delete?id=" + id, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 显示当前班级的所有学生
     *
     * @param batchID 当前班级ID
     * @return
     */
    @Override
    public String showStudents(Integer batchID) {
        return restTemplate.getForObject("http://" + SERVICE_NAME
                + "/batch/students/showAllStudents?batchID=" + batchID, String.class);
    }

    /**
     * 分页查询 所有
     *
     * @param batchId     班级ID
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    @Override
    public PageInfo<SkLmsStudentsCn> splitAllStudents(Integer batchId, Integer currentPage, Integer pageSize) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/batch/students/splitShowAll?batchId=" + batchId + "&currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);
    }


}

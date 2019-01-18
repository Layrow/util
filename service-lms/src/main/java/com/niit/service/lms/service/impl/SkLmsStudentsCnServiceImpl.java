package com.niit.service.lms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.common.utils.NumberUtil;
import com.niit.service.lms.dao.SkLmsBatchStudentCnMapper;
import com.niit.service.lms.dao.SkLmsStudentsCnMapper;
import com.niit.service.lms.pojo.SkLmsBatchStudentCn;
import com.niit.service.lms.pojo.SkLmsStudentsCn;
import com.niit.service.lms.service.SkLmsStudentsCnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.niit.common.utils.RandomUtil.randomChar;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/11/8
 * @since 1.0.0
 */
@Service
public class SkLmsStudentsCnServiceImpl implements SkLmsStudentsCnService {

    @Resource
    SkLmsStudentsCnMapper studentMapper;
    @Resource
    SkLmsBatchStudentCnMapper batchStudentCnMapper;


    /**
     * 实现单个添加学生的功能
     * 需要调用的方法有
     * 实现学生的添加
     * SkLmsStudentsCnMapper.insertSelective(SkLmsStudentsCn record)
     * 实现班级和学生的关联
     * SkLmsBatchStudentCnMapper.insertSelective(SkLmsBatchStudentCn record);
     *
     * @param student
     * @return
     */
    @Override
    public boolean addOne(SkLmsStudentsCn student, Integer batchID, String ClassName) {

        student.setStudentCreateTime(new Date());
        if (student.getStudentSchoolId() == null) {
            int num = studentMapper.selectAllStudentsByBatchCount(batchID);
            student.setStudentUserId(ClassName + NumberUtil.numFormat(num));
        } else {
            student.setStudentUserId(student.getStudentSchoolId());
        }
        student.setStudentPwd(randomChar(6));
        //将学生信息插入到数据库,只有插入进去才能获取生成的主键
        studentMapper.insertSelective(student);
        SkLmsBatchStudentCn batchStudentCn = new SkLmsBatchStudentCn();
        int stu_ID = student.getId();
        batchStudentCn.setStudentId(stu_ID);
        batchStudentCn.setBatchId(batchID);
        //添加到学生班级表
        return batchStudentCnMapper.insertSelective(batchStudentCn) > 0 ? true : false;

    }


    /**
     * 实现学生密码的重置,使用自动生成的密码
     *
     * @param id 需要修改的学生ID
     * @return
     */
    @Override
    public boolean reSetPassword(int id) {
        SkLmsStudentsCn student = studentMapper.selectByPrimaryKey(id);
        //调用工具类的随机生成字符串功能实现密码的重置
        student.setStudentPwd(randomChar(6));
        if (studentMapper.updateByPrimaryKey(student) > 0) {
            return true;
        } else {
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
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * 实现学生信息的修改功能
     *
     * @param student
     * @return 返回学生修改后的信息
     */
    @Override
    public boolean editStudentInfo(SkLmsStudentsCn student) {
        return studentMapper.updateByPrimaryKeySelective(student) > 0 ? true : false;
    }

    /**
     * 根据学生ID删除 学生
     *
     * @param id 学生id
     * @return 成功返回 true 失败返回 false
     */
    @Override
    public boolean deleteStudentByID(Integer id) {

        return
                (batchStudentCnMapper.deleteByStudentID(id) > 0 && studentMapper.deleteByPrimaryKey(id) > 0) ? true : false;
    }


    /**
     * 取得当前班级的所有学生和数量
     *
     * @param batchID 当前班级ID
     * @return
     */
    @Override
    public String showStudents(Integer batchID) {
        Map<String, Object> map = new HashMap<>(50);
        map.put("allBatchStudents", studentMapper.selectAllStudentsByBatch(batchID));
        map.put("allBatchStudentsCount", studentMapper.selectAllStudentsByBatchCount(batchID));
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);

    }

    /**
     * 导出班级的所有的学生
     *
     * @param batchID
     * @return
     */
    @Override
    public List<SkLmsStudentsCn> getAllStudents(Integer batchID) {
        return studentMapper.selectAllStudentsByBatch(batchID);
    }

    /**
     * 分页查询学生
     *
     * @param batchId     学生所在班级
     * @param currentPage 当前页,即想要的页数
     * @param pageSize    页面大小 想要一页显示多少学生
     * @return 返回 由分页组件处理
     */
    @Override
    public PageInfo<SkLmsStudentsCn> splitShowStudnets(Integer batchId, Integer currentPage, Integer pageSize) {
        List<SkLmsStudentsCn> list;
        PageInfo<SkLmsStudentsCn> listInfo;
        PageHelper.startPage(currentPage, pageSize);
        //执行SQL语句（list->分页后的数据）
        list = studentMapper.splitSelectAllStudents(batchId);
        //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
        listInfo = new PageInfo<>(list);
        return listInfo;
    }

    @Override
    public boolean exportExcel(List<SkLmsStudentsCn> list, String className, Integer batchId) {
        int count = 0;
        int lenth = list.size();
        Iterator<SkLmsStudentsCn> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.addOne(iterator.next(), batchId, className);
            count++;
        }
        return (count == lenth);
    }


}

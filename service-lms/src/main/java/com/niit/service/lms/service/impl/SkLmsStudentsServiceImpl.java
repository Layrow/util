package com.niit.service.lms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.dao.*;
import com.niit.service.lms.pojo.*;
import com.niit.service.lms.service.SkLmsStudentsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.08 12:42
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.08 12:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class SkLmsStudentsServiceImpl implements SkLmsStudentsService {

    @Autowired
    SkLmsBatchHomeworkCnMapper skLmsBatchHomeworkCnMapper;
    @Autowired
    SkLmsHomeworkCnMapper skLmsHomeworkCnMapper;
    @Autowired
    SkLmsHomeworkAnswerCnMapper skLmsHomeworkAnswerCnMapper;
    @Autowired
    SkLmsBatchCoursewareCnMapper skLmsBatchCoursewareCnMapper;
    @Autowired
    SkLmsCoursewareCnMapper skLmsCoursewareCnMapper;
    @Autowired
    SkLmsHomeworkAnswerAttachmentCnMapper skLmsHomeworkAnswerAttachmentCnMapper;

    //查找某班作业要求列表
    @Override
    public List<SkLmsHomeworkCn> selectHomeworkByBatch(int batch_id) {
        List<Integer> homeworkIdList = null;
        List<SkLmsHomeworkCn> homeworkList = null;
        homeworkIdList = skLmsBatchHomeworkCnMapper.selectByBatchId(batch_id);
        try {
            homeworkList = skLmsHomeworkCnMapper.selectByHomeworkIdList(homeworkIdList);
//            homeworkIdList.forEach(System.out::println);
        } catch (Exception e) {
            homeworkList = null;
        }
        return homeworkList;
    }

    //查找某班课件列表
    @Override
    public PageInfo<SkLmsCoursewareCn> selectCoursewareByBatch(int batch_id, Integer currentPage, Integer pageSize) {
        PageInfo<SkLmsCoursewareCn> pageInfo = null;
        List<Integer> coursewareIdList;
        List<SkLmsCoursewareCn> coursewareList;
        coursewareIdList = skLmsBatchCoursewareCnMapper.selectByBatchId(batch_id);
//        coursewareIdList.forEach(System.out::println);
        try {
            if(coursewareIdList.size()>0) {
                //开启分页
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                coursewareList = skLmsCoursewareCnMapper.selectByCoursewareIdList(coursewareIdList);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                pageInfo = new PageInfo<>(coursewareList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @Override
    public Integer testInsert(SkLmsHomeworkCn skLmsHomeworkCn) {
        return skLmsHomeworkCnMapper.insert(skLmsHomeworkCn);
    }

    @Override
    public Integer testInsert2(SkLmsBatchHomeworkCn skLmsBatchHomeworkCn) {
        return skLmsBatchHomeworkCnMapper.insert(skLmsBatchHomeworkCn);
    }

    //查找单项作业要求
    @Override
    public SkLmsHomeworkCn selectHomeworkById(int homework_id) {
        return skLmsHomeworkCnMapper.selectByPrimaryKey(homework_id);
    }

    //提交学生作业
    @Override
    public Integer insertStudentHomework(String json) {
        try {
            JSONObject jo = new JSONObject(json);

            //获取学生作业相关信息
            String homeworkAnswerContent = jo.get("homeworkAnswerContent").toString();
            Integer studentId = (Integer) jo.get("studentId");
            Integer homeworkId = (Integer) jo.get("homeworkId");

            //上传学生作业信息
            SkLmsHomeworkAnswerCn answer = new SkLmsHomeworkAnswerCn();
            answer.setHomeworkAnswerCreateTime(new Date());
            answer.setHomeworkAnswerContent(homeworkAnswerContent);
            answer.setHomeworkId(homeworkId);
            answer.setStudentId(studentId);
            skLmsHomeworkAnswerCnMapper.insert(answer);

            //获取学生作业ID(传给学生作业附件表)
            Integer homeworkAnswerId = skLmsHomeworkAnswerCnMapper.selectByStudentAndHomeworkId(homeworkId, studentId).getId();

            //获取学生作业多个附件（JSONArray）
            JSONArray fileUrls = jo.getJSONArray("fileUrls");
            JSONArray fileSizes = jo.getJSONArray("fileSizes");
            if (fileUrls != null) {
                for (int i = 0; i < fileUrls.length(); i++) {
                    //获取学生作业附件信息
                    //学生作业附件url
                    String fileUrl = (String) fileUrls.get(i);
                    //分割获取suffix与title
                    String[] fileSplit = fileUrl.split("/");
                    String fileSplitLast = fileSplit[fileSplit.length - 1];
                    String[] fileSplitSplit = fileSplitLast.split("\\.");
                    String suffix = fileSplitSplit[fileSplitSplit.length - 1];
                    String title = fileSplitSplit[fileSplitSplit.length - 2];

                    //上传学生作业附件
                    SkLmsHomeworkAnswerAttachmentCn attachment = new SkLmsHomeworkAnswerAttachmentCn();
                    attachment.setAnswerAttachmentUrl(fileUrl);
                    attachment.setAnswerAttachmentCreateTime(new Date());

                    attachment.setAnswerAttachmentSize((Integer)fileSizes.get(i));
                    attachment.setAnswerAttachmentSuffix(suffix);
                    attachment.setAnswerAttachmentTitle(title);
                    attachment.setHomeworkAnswerId(homeworkAnswerId);
                    skLmsHomeworkAnswerAttachmentCnMapper.insert(attachment);
                }
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //查看学生作业
    @Override
    public String selectHomeworkAnswerById(int homework_id, int student_id) {
        SkLmsHomeworkAnswerCn skLmsHomeworkAnswerCn = null;
        List<SkLmsHomeworkAnswerAttachmentCn> attachmentList = null;
        Map<String, Object> totalMap = new LinkedHashMap<>();

        try {
            //拿到学生作业pojo及其ID
            skLmsHomeworkAnswerCn = skLmsHomeworkAnswerCnMapper.selectByStudentAndHomeworkId(homework_id, student_id);
            Integer homeworkAnswerId = skLmsHomeworkAnswerCn.getId();
            //通过学生作业ID拿到学生作业附件列表
            attachmentList = skLmsHomeworkAnswerAttachmentCnMapper.selectByHomeworkAnswerId(homeworkAnswerId);
        } catch (Exception e) {
            return "";
        }

        //把学生作业pojo放入map
        totalMap.put("homeworkAnswer", skLmsHomeworkAnswerCn);

        //依据附件列表大小定义数组
        String[] urlArray = new String[attachmentList.size()];
        String[] titleAndSuffixArray = new String[attachmentList.size()];
        if (attachmentList.size() > 0) {
            for (int i = 0; i < attachmentList.size(); i++) {
                try {
                    urlArray[i] = attachmentList.get(i).getAnswerAttachmentUrl();
                    titleAndSuffixArray[i] = attachmentList.get(i).getAnswerAttachmentTitle() + "." + attachmentList.get(i).getAnswerAttachmentSuffix();
                } catch (Exception e) {
                    return "";
                }
            }
        }
        totalMap.put("urlArray", urlArray);
        totalMap.put("titleAndSuffixArray", titleAndSuffixArray);

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(totalMap);
    }

    //编辑学生作业
    @Override
    public int updateHomeworkAnswer(String json) {
        try {
            JSONObject jo = new JSONObject(json);

            //获取学生编辑作业相关信息
            String homeworkAnswerContent = jo.get("homeworkAnswerContent").toString();
            Integer studentId = (Integer) jo.get("studentId");
            Integer homeworkId = (Integer) jo.get("homeworkId");

            //获取学生已提交作业ID(作用1：编辑学生作业信息时 赋值)（作用2：赋值给编辑的附件列表）
            Integer homeworkAnswerId = skLmsHomeworkAnswerCnMapper.selectByStudentAndHomeworkId(homeworkId, studentId).getId();

            //上传编辑的学生作业信息
            SkLmsHomeworkAnswerCn answer = new SkLmsHomeworkAnswerCn();
            answer.setHomeworkAnswerCreateTime(new Date());
            answer.setHomeworkAnswerContent(homeworkAnswerContent);
            answer.setHomeworkId(homeworkId);
            answer.setStudentId(studentId);
            answer.setId(homeworkAnswerId);
            skLmsHomeworkAnswerCnMapper.updateByPrimaryKeySelective(answer);

            //编辑附件的时候不使用update 使用delete+insert
            //获取已提交的学生作业的附件ID列表
            List<SkLmsHomeworkAnswerAttachmentCn> attachmentList = skLmsHomeworkAnswerAttachmentCnMapper.selectByHomeworkAnswerId(homeworkAnswerId);
            List<Integer> attachmentIdList = attachmentList.stream().map(e -> e.getId()).collect(Collectors.toList());

            //根据附件ID列表删除附件
            if (attachmentList.size() != 0) {
                skLmsHomeworkAnswerAttachmentCnMapper.deleteByHomeworkAnswerIdList(attachmentIdList);
            }

            //获取学生编辑作业多个附件（JSONArray）
            JSONArray fileUrls = jo.getJSONArray("fileUrls");
            if (fileUrls != null) {
                for (int i = 0; i < fileUrls.length(); i++) {
                    //获取学生编辑作业附件信息
                    //学生编辑作业附件url
                    String fileUrl = (String) fileUrls.get(i);
                    //分割获取suffix与title
                    String[] fileSplit = fileUrl.split("/");
                    String fileSplitLast = fileSplit[fileSplit.length - 1];
                    String[] fileSplitSplit = fileSplitLast.split("\\.");
                    String suffix = fileSplitSplit[fileSplitSplit.length - 1];
                    String title = fileSplitSplit[fileSplitSplit.length - 2];

                    //上传学生编辑作业附件
                    SkLmsHomeworkAnswerAttachmentCn attachment = new SkLmsHomeworkAnswerAttachmentCn();
                    attachment.setAnswerAttachmentUrl(fileUrl);
                    attachment.setAnswerAttachmentCreateTime(new Date());
                    attachment.setAnswerAttachmentSize(10086);
                    attachment.setAnswerAttachmentSuffix(suffix);
                    attachment.setAnswerAttachmentTitle(title);
                    attachment.setHomeworkAnswerId(homeworkAnswerId);
                    skLmsHomeworkAnswerAttachmentCnMapper.insert(attachment);
                }
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

    }
}

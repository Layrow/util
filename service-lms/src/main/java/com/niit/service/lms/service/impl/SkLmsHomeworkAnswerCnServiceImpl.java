package com.niit.service.lms.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.dao.SkLmsHomeworkAnswerCnMapper;
import com.niit.service.lms.pojo.SkLmsHomeworkAnswerCn;
import com.niit.service.lms.service.SkLmsHomeworkAnswerCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @ClassName SkLmsHomeworkAnswerCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/23 10:38
 **/
@Service
public class SkLmsHomeworkAnswerCnServiceImpl implements SkLmsHomeworkAnswerCnService {

    @Autowired
    private SkLmsHomeworkAnswerCnMapper skLmsHomeworkAnswerCnMapper;

    @Override
    public String selectAnswerInfoByHomeIdAndStuId(Integer studentId, Integer homeworkId) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> listTitleAndSuffix = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        SkLmsHomeworkAnswerCn skLmsHomeworkAnswerCn = new SkLmsHomeworkAnswerCn();
//        SkLmsHomeworkAttachmentCn skLmsHomeworkAttachmentCn = new SkLmsHomeworkAttachmentCn();
        List<LinkedHashMap<String, Object>> linkedHashMaps = skLmsHomeworkAnswerCnMapper.selectAnswerInfoByHomeIdAndStuId(studentId, homeworkId);
        linkedHashMaps.forEach(homeworkAnswer -> {
            String s = null;
            String title = (String) homeworkAnswer.get("answer_attachment_title");
            String suffix = (String) homeworkAnswer.get("answer_attachment_suffix");
            if (title != null && suffix != null) {
                s = homeworkAnswer.get("answer_attachment_title") + "." + homeworkAnswer.get("answer_attachment_suffix");
            }
            String url = (String) homeworkAnswer.get("answer_attachment_url");
            listTitleAndSuffix.add(s);
            urlList.add(url);
            skLmsHomeworkAnswerCn.setHomeworkAnswerComments((String) homeworkAnswer.get("homework_answer_comments"));
            skLmsHomeworkAnswerCn.setHomeworkAnswerContent((String) homeworkAnswer.get("homework_answer_content"));
            skLmsHomeworkAnswerCn.setHomeworkAnswerCreateTime((Date) homeworkAnswer.get("homework_answer_create_time"));
            skLmsHomeworkAnswerCn.setHomeworkAnswerMark((Integer) homeworkAnswer.get("homework_answer_mark"));
            skLmsHomeworkAnswerCn.setStudentId((Integer) homeworkAnswer.get("student_id"));
            skLmsHomeworkAnswerCn.setId((Integer) homeworkAnswer.get("homework_answerid"));
        });
        map.put("skLmsHomeworkAnswerCn", skLmsHomeworkAnswerCn);
        map.put("titleAndSuffix", listTitleAndSuffix);
        map.put("urlList", urlList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    @Override
    public int updateByPrimaryKeySelective(SkLmsHomeworkAnswerCn record) {
        return skLmsHomeworkAnswerCnMapper.updateByPrimaryKeySelective(record);
    }

    // 学生作业信息的分析
    @Override
    public String selectStudentHomeworkInfo(Integer batchId, Integer studentId) {
        HashMap<String, Object> map = new HashMap<>();
        List<HashMap<String, Object>> scoreList = skLmsHomeworkAnswerCnMapper.selectScoreByStudentId(studentId);
        List<HashMap<String, Object>> homeworkList = skLmsHomeworkAnswerCnMapper.selectHomeworkSizeByStudentId(batchId, studentId);
        map.put("scoreList",scoreList);
        map.put("homeworkList", homeworkList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    @Override
    public String selectBacthInfo(Integer batchId) {
        List<HashMap<String, Object>> maps = skLmsHomeworkAnswerCnMapper.selectBacthInfo(batchId);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(maps);
    }
}

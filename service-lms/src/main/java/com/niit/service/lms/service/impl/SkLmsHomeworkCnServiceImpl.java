package com.niit.service.lms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.dao.SkLmsHomeworkCnMapper;
import com.niit.service.lms.pojo.SkLmsBatchHomeworkCn;
import com.niit.service.lms.pojo.SkLmsHomeworkAttachmentCn;
import com.niit.service.lms.pojo.SkLmsHomeworkCn;
import com.niit.service.lms.service.SkLmsBatchHomeworkCnService;
import com.niit.service.lms.service.SkLmsHomeworkAttachmentCnSerive;
import com.niit.service.lms.service.SkLmsHomeworkCnService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName SkLmsHomeworkCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/11/8 15:34
 **/
@Service
public class SkLmsHomeworkCnServiceImpl implements SkLmsHomeworkCnService {

    @Autowired
    private SkLmsHomeworkCnMapper skLmsHomeworkCnMapper;
    @Autowired
    private SkLmsHomeworkAttachmentCnSerive skLmsHomeworkAttachmentCnSerive;
    @Autowired
    private SkLmsBatchHomeworkCnService skLmsBatchHomeworkCnService;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return skLmsHomeworkCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkLmsHomeworkCn record) {
        return skLmsHomeworkCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkLmsHomeworkCn record) {
        return skLmsHomeworkCnMapper.insertSelective(record);
    }

    @Override
    public String selectByPrimaryKey(Integer id) {
        Map<String, Object> map = new HashMap<>();
        SkLmsHomeworkCn skLmsHomeworkCn = skLmsHomeworkCnMapper.selectPrimaryKey(id);
        List<SkLmsHomeworkAttachmentCn> skLmsHomeworkAttachmentCn = skLmsHomeworkCn.getSkLmsHomeworkAttachmentCn();
        List<String> suffixAndTitleList = new ArrayList<>();
        skLmsHomeworkAttachmentCn.forEach(x -> {
            String suffixAndTitle = x.getHomeworkAttachmentTitle() +"."+ x.getHomeworkAttachmentSuffix();
            suffixAndTitleList.add(suffixAndTitle);
        });
        map.put("skLmsHomeworkCn", skLmsHomeworkCn);
        map.put("suffixAndTitleList", suffixAndTitleList);

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);

    }

    @Override
    public int updateByPrimaryKeySelective(SkLmsHomeworkCn record) {
        return skLmsHomeworkCnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SkLmsHomeworkCn record) {
        return skLmsHomeworkCnMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(SkLmsHomeworkCn record) {
        return skLmsHomeworkCnMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SkLmsHomeworkCn> selectByFacultyId(Integer facultyId) {
        return skLmsHomeworkCnMapper.selectByFacultyId(facultyId);
    }

    // 布置作业 上传附件 共享班级
    @Override
    @Transactional
    public void insertHomework(String json) throws Exception {
        // 分解json，获取所有信息
        JSONObject jsonObject = new JSONObject(json);
        String facultyId = jsonObject.getString("facultyId");
        String homeworkTitle = jsonObject.getString("homeworkTitle");
        String homeworkContent = jsonObject.getString("homeworkContent");
        JSONArray urlStr = jsonObject.getJSONArray("url");
        JSONArray ids = jsonObject.getJSONArray("ids");

        // 封装作业信息对象
        SkLmsHomeworkCn skLmsHomeworkCn = new SkLmsHomeworkCn();
        skLmsHomeworkCn.setFacultyId(facultyId);
        skLmsHomeworkCn.setHomeworkTitle(homeworkTitle);
        skLmsHomeworkCn.setHomeworkCreateTime(new Date());
        skLmsHomeworkCn.setHomeworkContent(homeworkContent);
        skLmsHomeworkCn.setHomeworkStatus(1);
        // 插入作业信息
        skLmsHomeworkCnMapper.insertSelective(skLmsHomeworkCn);
        // 获取本次布置的作业的ID,mapper中设置了mybatis的配置，会返回自增主键到实体上
        int homeworkId = skLmsHomeworkCn.getId();
        //Integer homeworkId = selectMaxHomeworkId();

        for (int i = 0; i < urlStr.length(); i++) {
            String url = (String) urlStr.get(i);
            // 获得 suffix and title
            String[] fileSplit = url.split("/");
            String fileSplitLast=fileSplit[fileSplit.length-1];
            String[] fileSplitSplit=fileSplitLast.split("\\.");
            String suffix = fileSplitSplit[fileSplitSplit.length-1];
            String title = fileSplitSplit[fileSplitSplit.length - 2];

            Map<String, Object> fileInfo = getFileInfo(url);
            // 插入课件信息
            SkLmsHomeworkAttachmentCn skLmsHomeworkAttachmentCn = new SkLmsHomeworkAttachmentCn();
            skLmsHomeworkAttachmentCn.setHomeworkId(homeworkId);
            skLmsHomeworkAttachmentCn.setHomeworkAttachmentUrl(url);
            skLmsHomeworkAttachmentCn.setHomeworkAttachmentSize((Integer) fileInfo.get("size"));
            skLmsHomeworkAttachmentCn.setHomeworkAttachmentSuffix(suffix);
            skLmsHomeworkAttachmentCn.setHomeworkAttachmentCreateTime(new Date());
            skLmsHomeworkAttachmentCn.setHomeworkAttachmentTitle(title);
            skLmsHomeworkAttachmentCnSerive.insertSelective(skLmsHomeworkAttachmentCn);
        }

        for (int i = 0; i < ids.length(); i++) {
            Integer id = (Integer) ids.get(i);
            // 插入共享班级ID信息
            SkLmsBatchHomeworkCn skLmsBatchHomeworkCn = new SkLmsBatchHomeworkCn();
            skLmsBatchHomeworkCn.setBatchId(id);
            skLmsBatchHomeworkCn.setHomeworkId(homeworkId);
            skLmsBatchHomeworkCnService.insertSelective(skLmsBatchHomeworkCn);
        }

    }

    // 更新作业
    @Override
    public void updateHomework(String json) throws Exception {
        // 获取json信息
        JSONObject jsonObject = new JSONObject(json);
        String facultyId =  jsonObject.getString("facultyId");
        String homeworkTitle = jsonObject.getString("homeworkTitle");
        String homeworkContent = jsonObject.getString("homeworkContent");
        Integer homeworkId = (Integer) jsonObject.get("homeworkId");
        JSONArray urlStr = jsonObject.getJSONArray("url");

        // 作业信息
        SkLmsHomeworkCn skLmsHomeworkCn = new SkLmsHomeworkCn();
        // Integer homeworkId = selectMaxHomeworkId();
        skLmsHomeworkCn.setId(homeworkId);
        skLmsHomeworkCn.setFacultyId(facultyId);
        if (!("".equals(homeworkTitle))) {
            skLmsHomeworkCn.setHomeworkTitle(homeworkTitle);
        }
        if (!("".equals(homeworkContent))) {
            skLmsHomeworkCn.setHomeworkContent(homeworkContent);
        }
        skLmsHomeworkCn.setHomeworkStatus(1);
        // 插入作业信息
        skLmsHomeworkCnMapper.updateByPrimaryKeySelective(skLmsHomeworkCn);
        // 获取本次布置的作业的ID

        // 保存的时候进行附件删除重新插入
        skLmsHomeworkAttachmentCnSerive.deleteByHomeworkId(homeworkId);
        // 附件信息
        if (urlStr!=null) {
            for (int i = 0; i < urlStr.length(); i++) {
                String url = (String) urlStr.get(i);
                // 获得 suffix and title
                String[] fileSplit = url.split("/");
                String fileSplitLast=fileSplit[fileSplit.length-1];
                String[] fileSplitSplit=fileSplitLast.split("\\.");
                String suffix = fileSplitSplit[fileSplitSplit.length-1];
                String title = fileSplitSplit[fileSplitSplit.length - 2];

                Map<String, Object> fileInfo = getFileInfo(url);
                // 插入附件信息
                SkLmsHomeworkAttachmentCn skLmsHomeworkAttachmentCn = new SkLmsHomeworkAttachmentCn();
                skLmsHomeworkAttachmentCn.setHomeworkId(homeworkId);
                skLmsHomeworkAttachmentCn.setHomeworkAttachmentUrl(url);
                skLmsHomeworkAttachmentCn.setHomeworkAttachmentSize((Integer) fileInfo.get("size"));
                skLmsHomeworkAttachmentCn.setHomeworkAttachmentSuffix(suffix);
                skLmsHomeworkAttachmentCn.setHomeworkAttachmentCreateTime(new Date());
                skLmsHomeworkAttachmentCn.setHomeworkAttachmentTitle(title);
                skLmsHomeworkAttachmentCnSerive.insertSelective(skLmsHomeworkAttachmentCn);
            }
        }

    }

    // 删除班级作业 ??? 一条sql实现？？
    @Override
    @Transactional
    public void deleteHomework(Integer homeworkId) {
        // 删除作业
        deleteByPrimaryKey(homeworkId);
        // 删除附件
        skLmsHomeworkAttachmentCnSerive.deleteByHomeworkId(homeworkId);
        // 删除作业班级关系
        skLmsBatchHomeworkCnService.deleteByHomeworkId(homeworkId);
    }


    @Override
    public List<SkLmsHomeworkCn> selectHomeworkByFacultyId(Integer facultyId) {
        return skLmsHomeworkCnMapper.selectHomeworkByFacultyId(facultyId);
    }

    @Override
    public String selectStudentByHomeworkId(Integer batchId,Integer homeworkId,Integer currentPage, Integer pageSize) {
        // currentPage 第几页 pageSize 每页大小
        Map<String, Object> map = new LinkedHashMap<>();
        int count = skLmsHomeworkCnMapper.selectFinishCountByHomeworkId(batchId,homeworkId);
        PageInfo<LinkedHashMap<String, Object>> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        List<LinkedHashMap<String, Object>> commitInfoList = skLmsHomeworkCnMapper.selectStudentByHomeworkId(batchId,homeworkId);
        listInfo = new PageInfo<>(commitInfoList);
        map.put("info", listInfo);
        map.put("commitcount", count);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    @Override
    public List<LinkedHashMap<String, Object>> selectNotdoStudentByHomeworkId(Integer batchId, Integer homeworkId) {
        return skLmsHomeworkCnMapper.selectNotdoStudentByHomeworkId(batchId,homeworkId);
    }

    @Override
    public String selectHomeworkByBatchId(Integer batchId) {
        Map<String, Object> map = new HashMap<>();
        List<String> timeList = new ArrayList<>();
        List<String> formatTimeList = new ArrayList<>();
        List<SkLmsHomeworkCn> skLmsHomeworkCnList = skLmsHomeworkCnMapper.selectHomeworkByBatchId(batchId);
        if (!skLmsHomeworkCnList.isEmpty()) {
            skLmsHomeworkCnList.forEach(homework ->{
                Date time = homework.getHomeworkCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
                String s = sdf.format(time);
                String s1 = formatTime.format(time);
                timeList.add(s);
                formatTimeList.add(s1);
            });

        }
        map.put("skLmsHomeworkCnList", skLmsHomeworkCnList);
        map.put("timeList", timeList);
        map.put("timeList2", formatTimeList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    // 获得特定班级特定作业下的得分情况
    @Override
    public String selectScore(Integer batchId, Integer homeworkId) {
        List<HashMap<String,Object>> maps = skLmsHomeworkCnMapper.selectScore(batchId, homeworkId);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(maps);
    }

    // String --> List
    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }

/*    // 获得文件信息
    public Map<String, Object> getFileInfo(String strUrl) {
            Map<String, Object> fileInfo = new HashMap<>();
            BufferedInputStream bis = null;
            HttpURLConnection urlconnection = null;
        try {
            URL url = new URL(strUrl);
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.connect();
            bis = new BufferedInputStream(urlconnection.getInputStream());
            // 获取文件后缀名
            String suffix = HttpURLConnection.guessContentTypeFromStream(bis);
            // 获取文件大小
            Integer size = urlconnection.getContentLength();
            // 获取文件信息
            String fileField= urlconnection.getHeaderField("Content-Disposition");
            // fileInfo.put("suffix",suffix);
            fileInfo.put("size", size);
            // fileInfo.put("fileField", fileField);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlconnection.disconnect();
        }
        return fileInfo;
    }*/

    // 获得文件信息
    public Map<String, Object> getFileInfo(String strUrl) {
        Map<String, Object> fileInfo = new HashMap<>();
        BufferedInputStream bis = null;
        HttpURLConnection urlconnection = null;
        try {
            URL url = new URL(strUrl);
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.connect();
            // 获取文件大小
            Integer size = urlconnection.getContentLength();
            fileInfo.put("size", size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }
}

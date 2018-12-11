package com.niit.service.lms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.lms.dao.SkLmsCoursewareCnMapper;
import com.niit.service.lms.pojo.SkLmsCoursewareCn;
import com.niit.service.lms.service.SkLmsCoursewareCnService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @Auther: huangwei
 * @Date: 2018/11/8 15:52
 * @Description:
 */
@Service
public class SkLmsCoursewareCnServiceImpl implements SkLmsCoursewareCnService {
    @Autowired
    private SkLmsCoursewareCnMapper sccm;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sccm.deleteByPrimaryKey(id);
    }

    //查询所有
    @Override
    public String selectAll(Integer facultyId,int currentPage, int pageSize,String courseware_title) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<SkLmsCoursewareCn> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        List<SkLmsCoursewareCn> list = sccm.selectAll(facultyId,courseware_title);
        listInfo = new PageInfo<>(list);
        String[] titleAndSuffixArray = new String[list.size()];
        String[] strings = new String[list.size()];
        String[] times   =  new String[list.size()];
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    strings[i] = list.get(i).getCoursewareUrl();
                    titleAndSuffixArray[i] = list.get(i).getCoursewareTitle()+"."+list.get(i).getCoursewareSuffix();
                    Date time = list.get(i).getCoursewareCreateTime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    times[i] = sdf.format(time);
                } catch (Exception e) {
                    return "";
                }
            }
            map.put("titleAndSuffixArray",titleAndSuffixArray);
            map.put("times",times);

        }
        map.put("SkLmsCoursewareCn",listInfo);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }
    //上传文件
    public String JSONTokener(String in) {
        if (in != null && in.startsWith("\ufeff")) {
            in = in.substring(1);
        }
        return in;
    }
    @Override
    public int insertSelective(String url) {
        try {

            JSONObject obj = new JSONObject(JSONTokener(url));

            String fileUrls = obj.getString("fileUrls");
            //分割获取suffix与title
                String[] fileSplit = fileUrls.split("/");
                String fileSplitLast = fileSplit[fileSplit.length - 1];
                String[] fileSplitSplit = fileSplitLast.split("\\.");
                String suffix = fileSplitSplit[fileSplitSplit.length - 1];
                String title = fileSplitSplit[fileSplitSplit.length - 2];
                try {
/*
                    URL url1 = new URL(fileUrl);
                    HttpURLConnection conn = null;
                    conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("HEAD");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
                    int size = conn.getContentLength();*/

                    //创建对象往库里插入
                    SkLmsCoursewareCn cn = new SkLmsCoursewareCn();
                    cn.setCoursewareCreateTime(new Date());  //日期
                    cn.setCoursewareTitle(title);   //标题
                    cn.setCoursewareSuffix(suffix);
                    cn.setCoursewareUrl(fileUrls);
                   // cn.setCoursewareSize(size);

                    //插入
                    sccm.insertSelective(cn);

                } catch (Exception e) {
                    e.printStackTrace();
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }
    @Override
    public String selectAllWare(Integer facultyId,int currentPage ,int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<SkLmsCoursewareCn> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        List<SkLmsCoursewareCn> list = sccm.selectAllWare(facultyId);
        listInfo = new PageInfo<>(list);
        String[] titleAndSuffixArray = new String[list.size()];
        String[] strings = new String[list.size()];
        String[] times   =  new String[list.size()];
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    strings[i] = list.get(i).getCoursewareUrl();
                    titleAndSuffixArray[i] = list.get(i).getCoursewareTitle()+"."+list.get(i).getCoursewareSuffix();
                    Date time = list.get(i).getCoursewareCreateTime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    times[i] = sdf.format(time);
                } catch (Exception e) {
                    return "";
                }
            }
            map.put("titleAndSuffixArray",titleAndSuffixArray);
            map.put("times",times);

        }
        map.put("SkLmsCoursewareCn",listInfo);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    @Override
    public String selectAllBatchWare(Integer batchId,int currentPage ,int pageSize ) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<SkLmsCoursewareCn> listInfo;
        PageHelper.startPage(currentPage,pageSize);
        List<SkLmsCoursewareCn> list = sccm.selectAllBatchWare(batchId);
        listInfo = new PageInfo<>(list);
        String[] titleAndSuffixArray = new String[list.size()];
        String[] strings = new String[list.size()];
        String[] times   =  new String[list.size()];
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    strings[i] = list.get(i).getCoursewareUrl();
                    titleAndSuffixArray[i] = list.get(i).getCoursewareTitle()+"."+list.get(i).getCoursewareSuffix();
                    Date time = list.get(i).getCoursewareCreateTime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    times[i] = sdf.format(time);
                } catch (Exception e) {
                    return "";
                }
            }
            map.put("titleAndSuffixArray",titleAndSuffixArray);
            map.put("times",times);
        }
        map.put("SkLmsCoursewareCn",listInfo);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);

    }
}

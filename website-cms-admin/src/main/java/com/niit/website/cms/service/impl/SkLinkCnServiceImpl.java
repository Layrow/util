package com.niit.website.cms.service.impl;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkLinkCn;
import com.niit.website.cms.service.SkLinkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SkLinkCnServiceImpl implements SkLinkCnService {


    final String SERVICE_NAME = "service-cms";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int insert(SkLinkCn record,String locale) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/link?locale="+locale, record, String.class);
        return 999;
    }


    @Override
    public int updateByPrimaryKey(SkLinkCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/link?locale="+locale, record);
        return 999;
    }

    @Override
    public int updateList(List<SkLinkCn> list, String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/updateList?locale="+locale, list);
        return 999;
    }

    @Override
    public PageInfo<SkLinkCn> selectByPage(int currentPage, int pageSize,String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/link?currentPage=" + currentPage + "&pageSize=" + pageSize+"&locale="+locale, PageInfo.class);
    }

    @Override
    public Integer updateByList(List<SkLinkCn> record, String locale) {
        try {
            restTemplate.put("http://" + SERVICE_NAME + "/linklist?local=" + locale, record);
            return 999;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean deleteBatchLinkByPromaryKey(String ids,String locale) {
        try {
            restTemplate.delete("http://" + SERVICE_NAME + "/delLink?linkID=" + ids+"&locale="+locale, ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean check(String ids,String locale) {
        try {
            restTemplate.put("http://" + SERVICE_NAME + "/checkLink?linkID=" + ids+"&locale="+locale, ids,locale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 模糊查询实现
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param title       模糊关键字
     * @param locale      语言
     * @return
     */
    @Override
    public PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize, String title, String locale) {
        PageInfo<SkLinkCn> list=null;
        try {
            list=restTemplate.postForObject("http://" + SERVICE_NAME + "/link/likeSelectAll?currentPage="+currentPage+"&pageSize="+pageSize+"&title="+title+"&locale="+locale,null,PageInfo.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有的连接
     *
     * @param currentPage
     * @param pageSize
     * @param locale
     * @return
     */
    @Override
    public PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize, String locale) {
        PageInfo<SkLinkCn> list=null;
        try {
            list=restTemplate.postForObject("http://" + SERVICE_NAME + "/link/listAllLink?currentPage="+currentPage+"&pageSize="+pageSize+"&locale="+locale,null,PageInfo.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

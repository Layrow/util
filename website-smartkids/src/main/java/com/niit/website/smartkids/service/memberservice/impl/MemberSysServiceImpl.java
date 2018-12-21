package com.niit.website.smartkids.service.memberservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationSystem;
import com.niit.website.smartkids.service.memberservice.IMemberNotificationSys;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
@Service
public class MemberSysServiceImpl implements IMemberNotificationSys {

    private static final  String SERVICE_NAME = "service-member";
    private static final  String URL="http://"+SERVICE_NAME+"/system/";

    @Resource
    RestTemplate restTemplate;

    /**
     * 增加一条系统通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean add(SkMemberNotificationSystem record) {
       try {
           restTemplate.postForObject(URL+"add",record,String.class);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    /**
     * 删除一条系统通知
     *
     * @param sId
     * @return
     */
    @Override
    public boolean delete(String sId) {
        try {
            restTemplate.delete(URL+"delete?sId="+sId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    /**
     * 修改一条系统通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean update(SkMemberNotificationSystem record) {
        try {
            restTemplate.put(URL+"edit",record);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }

    /**
     * 显示所有的 系统通知
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SkMemberNotificationSystem> listAll(Integer currentPage, Integer pageSize) {
        return  restTemplate.postForObject(URL+"listAll?currentPage="
                +currentPage+"&pageSize="+pageSize,null,PageInfo.class);
    }
}

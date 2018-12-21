package com.niit.website.smartkids.service.memberservice.impl;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.member.SkMemberNotificationOps;
import com.niit.website.smartkids.service.memberservice.IMemberNoficationOps;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/20
 * @since 1.0.0
 */
@Service
public class MemberOpsServiceImpl implements IMemberNoficationOps {

    private static final  String SERVICE_NAME = "service-member";
    private static final  String URL="http://"+SERVICE_NAME+"/notification/";

    @Resource
    RestTemplate restTemplate;
    /**
     * 添加一条通知
     *
     * @param record
     * @return
     */
    @Override
    public boolean insertNotification(SkMemberNotificationOps record) {

           try {
               restTemplate.postForObject(URL+"/add",record,String.class);
               return true;
           }catch (Exception e){
               return false;
           }

    }

    /**
     * 根据通知主键删除通知
     *
     * @param nId
     * @return
     */
    @Override
    public boolean deleteNotification(Integer nId) {

        try {
            restTemplate.delete(URL+"delete?nId="+nId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询用户下的所有通知
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param uId
     * @return
     */
    @Override
    public PageInfo<SkMemberNotificationOps> listAll(Integer currentPage, Integer pageSize, Integer uId) {
        return  restTemplate.postForObject(URL+"listAll?currentPage="+currentPage+"&pageSize="+pageSize+"&uId="+uId,null,PageInfo.class);
    }
}

package com.niit.website.smartkids.service.memberservice.impl;

import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.service.memberservice.IMemberItegralService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/19
 * @since 1.0.0
 */
@Service
public class MemberServiceIntegralmpl implements IMemberItegralService {

    private static final  String SERVICE_NAME = "service-member";
    private static final  String URL="http://"+SERVICE_NAME+"/integral/";

    @Resource
    RestTemplate restTemplate;

    /**
     * 取得指定用户的所有积分
     *
     * @param uid
     * @return
     */
    @Override
    public String getTatal(Integer uid) {
        try {

            String tatal= restTemplate.getForObject(URL+"total?uid="+uid,String.class);
            return tatal;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增加操作记录
     *
     * @param record
     * @return
     */
    @Override
    public String interAction(SkMemberIntegral record) {
            return restTemplate.postForObject(URL+"integral",record,String.class);
    }
}

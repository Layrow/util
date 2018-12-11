package com.niit.website.smartkids.service.impl;

import com.niit.website.smartkids.pojo.SkAd;
import com.niit.website.smartkids.pojo.SkAdContent;
import com.niit.website.smartkids.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName SkAdServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 16:13
 **/
@Service
public class SkAdServiceImpl implements SkAdService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    private RestTemplate restTemplate;

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    @Override
    public List<SkAdContent> selectByAdId(Integer adId, Integer status) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId, List.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId + "&status=" + status, List.class);

    }

    // 查询所有广告位(可用/非可用)
    @Override
    public List<SkAd> selectAllAdsense(Integer status) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad",List.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad?status=" + status,List.class);
    }
}

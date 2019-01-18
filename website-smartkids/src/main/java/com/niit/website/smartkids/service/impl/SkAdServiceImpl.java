package com.niit.website.smartkids.service.impl;

import com.github.pagehelper.PageInfo;
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
    public PageInfo<SkAdContent> selectByAdId(Integer adId, Integer status, Integer currentPage, Integer pageSize) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId + "&currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId + "&status=" + status + "&currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);
    }

    // 查询所有广告位(可用/非可用)
    @Override
    public PageInfo<SkAd> selectAllAdsense(Integer status, Integer currentPage, Integer pageSize) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad?currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad?status=" + status + "&currentPage=" + currentPage + "&pageSize=" + pageSize, PageInfo.class);
    }
}

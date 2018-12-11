package com.niit.website.cms.service.impl;

import com.niit.website.cms.pojo.SkAd;
import com.niit.website.cms.pojo.SkAdContent;
import com.niit.website.cms.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName SkAdSerivceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 10:02
 **/
@Service
public class SkAdSerivceImpl implements SkAdService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    private RestTemplate restTemplate;

    // 查询所有广告位(可用/非可用)
    @Override
    public List<SkAd> selectAllAdsense(Integer status) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad",List.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad?status=" + status,List.class);
    }

    // 根据ID批量删除广告位
    @Override
    public void deleteMoreAd(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/ad?id=" + id);
    }

    // 根据ID删除广告
    @Override
    public void deleteAdContentByPrimaryKey(Integer id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/ad/content?id=" + id);
    }

    // 新增特定广告位下的广告
    @Override
    public void insertAdContentSelective(SkAdContent record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/ad/content", record,String.class);
    }

    // 审核广告
    @Override
    public void updateByPrimaryKeySelective(SkAdContent record) {
        restTemplate.put("http://" + SERVICE_NAME + "/ad",record);
    }

    // 删除某个广告位下的所有广告
    @Override
    public void deleteByAdId(Integer adId) {
        restTemplate.delete("http://" + SERVICE_NAME + "/ad/contents?adId="+adId);
    }

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    @Override
    public List<SkAdContent> selectByAdId(Integer adId, Integer status) {
        if ("".equals(status) || status == null) {
            return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId, List.class);
        }
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/ad/content?adId=" + adId + "&status=" + status, List.class);

    }

    // 新增广告位
    @Override
    public void insert(SkAd record) {
        restTemplate.postForObject("http://" + SERVICE_NAME + "/ad", record,String.class);
    }

    // 批量审核广告
    @Override
    public void updateMoreAdContent(String id) {
        restTemplate.put("http://" + SERVICE_NAME + "/ad?id=" + id,id);
    }

    // 批量删除广告
    @Override
    public void deleteMoreAdContent(String id) {
        restTemplate.delete("http://" + SERVICE_NAME + "/ad/content?id=" + id,id);
    }
}

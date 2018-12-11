package com.niit.website.cms.service.impl;

import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkChannelCn;
import com.niit.website.cms.service.SkChannelCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * @ClassName SkChannelCnServiceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/10/30 20:06
 **/
@Service
public class SkChannelCnServiceImpl implements SkChannelCnService {

    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int insertSelective(SkChannelCn record,String locale) {
        BaseResult<String> result = restTemplate.postForObject("http://" + SERVICE_NAME + "/skChannelCn/channel/"+locale, record, BaseResult.class);
/*        if (result.getSuccess()) {
            return Tools.isEmpty(result.getContent()) ? 0 : Integer.parseInt(result.getContent());
        } else {
            return 0;
        }*/
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/skChannelCn/channel",record);
        return 1;
    }

    @Override
    public List<SkChannelCn> selectAllChannel(String locale) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/skChannelCn/channel/"+locale,List.class);
    }

    @Override
    public int deleteMoreChannel(String id,String locale) {
        restTemplate.delete("http://" + SERVICE_NAME + "/skChannelCn/channel/{locale}/{id}",locale,id);
        return 1;
    }
}

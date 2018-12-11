package com.niit.website.cms.service.impl;

import com.niit.common.entity.BaseResult;
import com.niit.website.cms.pojo.SkArticleCategoryCn;
import com.niit.website.cms.service.SkArticleCategoryCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName SkArticleCategoryCnServiceImpl
 * @Description SkArticleCategoryCnService接口实现类
 * @Author liyuhao
 * @Date 2018/10/3015:10
 **/
@Service
public class SkArticleCategoryCnServiceImpl implements SkArticleCategoryCnService {
    // 采用服务名而没有采用地址 负载均衡
    final String SERVICE_NAME = "service-cms";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getParentList(List<Integer> categoryIds, Integer channelId, String locale) {
        return restTemplate.postForObject("http://" + SERVICE_NAME + "//skArticleCategoryCn/parent_list?channelId="+channelId+"&locale="+locale,categoryIds,String.class);
    }

    @Override
    public int insertSelective(SkArticleCategoryCn record,String locale) {
        // REST资源的url
        BaseResult<String> result = restTemplate.postForObject(
                "http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/"+locale, record, BaseResult.class);
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(SkArticleCategoryCn record,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/"+locale,record);
        return 1;
    }

    // 删除多条栏目类别记录
    @Override
    public int deleteMoreArticleCategoryCn(String id,String locale) {
        // 路径绑定id
        restTemplate.delete("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/{locale}/{id}",locale,id);
        return 1;
    }

    // 查询所有栏目类别(包含层次结构)
    // web端用的getForObject会将子级数据类型存储到LinkedHashMap。子级别数据key都是childen，key相同，值将会被覆盖
    @Override
    public List<SkArticleCategoryCn> getNodeTree(Integer channelId,String locale) {
        ParameterizedTypeReference<List<SkArticleCategoryCn>> typeRef = new ParameterizedTypeReference<List<SkArticleCategoryCn>>() {
        };
        ResponseEntity<List<SkArticleCategoryCn>> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/"+locale+"/"+channelId, HttpMethod.GET, null, typeRef);
        List<SkArticleCategoryCn> skArticleCategoryCnList = responseEntity.getBody();
        return skArticleCategoryCnList;
    }

    @Override
    public void updateMoreSortId(List<SkArticleCategoryCn> skArticleCategoryCnList,String locale) {
        restTemplate.put("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/moreArticleCategory/{locale}",skArticleCategoryCnList,locale);
    }

    // 根据parent_id查询该id下的下一层栏目
    @Override
    public List<SkArticleCategoryCn> getNextInfos(Integer parentId) {
        ParameterizedTypeReference<List<SkArticleCategoryCn>> typeRef = new ParameterizedTypeReference<List<SkArticleCategoryCn>>() {
        };
        ResponseEntity<List<SkArticleCategoryCn>> responseEntity = restTemplate.exchange("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory/next/" + parentId, HttpMethod.GET, null, typeRef);
        List<SkArticleCategoryCn> skArticleCategoryCnList = responseEntity.getBody();
        return skArticleCategoryCnList;
    }

    // 查询所有
    @Override
    public List<SkArticleCategoryCn> selectAllCategory() {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/skArticleCategoryCn/articleCategory",List.class);
    }

}

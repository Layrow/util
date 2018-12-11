package com.niit.website.bbs.service.impl;


import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.website.bbs.pojo.SkBbsSection;
import com.niit.website.bbs.service.SkBbsSectionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/6
 * @since 1.0.0
 */
@Service
public class SkBbsSectionServiceImpl implements SkBbsSectionService {
    private static final  String SERVICE_NAME = "service-bbs";
    private static final  String URL="http://"+SERVICE_NAME+"/section/";
    @Resource
    RestTemplate restTemplate;

    /**
     * 取得栏目名
     * @param sectionId
     * @return
     */
    @Override
    public String getName(Integer sectionId) {
        return restTemplate.getForObject(URL+"getName?sectionId="+sectionId,String.class);
    }

    /**
     * 增加 模块功能
     *
     * @param section
     * @return
     */
    @Override
    public boolean addSection(SkBbsSection section) {
       String result =
               restTemplate.postForObject(URL+"add",section,String.class);
       if (Tools.isEmpty(result)){
           return false;
       }else {
           return true;
       }
    }

    /**
     * 删除栏目,根据主键
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSection(String id) {
        try {
            restTemplate.delete(URL+"delete?id="+id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量跟新 栏目排序值
     *
     * @param list
     * @return
     */
    @Override
    public boolean updateOderBatch(List<SkBbsSection> list) {
        try {
            restTemplate.put(URL+"edit",list);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询所有的栏目,按照排序值升序排列
     *
     * @return 栏目对象排序后的
     */
    @Override
    public List<SkBbsSection> selectAllSection() {
        return restTemplate.getForObject(URL+"list",List.class);
    }

    @Override
    public boolean editSection(SkBbsSection section) {
        try {
            restTemplate.put(URL+"editOne",section);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * 分页模糊查询
     *
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<SkBbsSection> likeSelectAll(Integer currentPage, Integer pageSize, String name) {
        return  restTemplate.getForObject(URL+"like?currentPage="+currentPage+"&pageSize="+pageSize+"&name="+name,PageInfo.class);
    }
}

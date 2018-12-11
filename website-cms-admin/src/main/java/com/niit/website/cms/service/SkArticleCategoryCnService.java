package com.niit.website.cms.service;


import com.niit.website.cms.pojo.SkArticleCategoryCn;

import java.util.List;

/**
 * @Description ArticleCategoryCnService服务
 * @Author liyuhao
 * @Date 2018/10/30 20:01
 **/
public interface SkArticleCategoryCnService {
    String getParentList(List<Integer> categoryIds, Integer channelId, String locale);


    int insertSelective(SkArticleCategoryCn record,String locale);

    int updateByPrimaryKeySelective(SkArticleCategoryCn record,String locale);

    // 批量删除栏目类别
    int deleteMoreArticleCategoryCn(String id,String locale);

    // 查询栏目类别节点树，查询所有栏目类别(包含层级关系)
    List<SkArticleCategoryCn> getNodeTree(Integer channelId,String locale);

    // 批量修改sortId
    void updateMoreSortId(List<SkArticleCategoryCn> skArticleCategoryCnList,String locale);

    // 根据父类id查询对应的子类
    List<SkArticleCategoryCn> getNextInfos(Integer parentId);

    // 查询所有
    List<SkArticleCategoryCn> selectAllCategory();

}

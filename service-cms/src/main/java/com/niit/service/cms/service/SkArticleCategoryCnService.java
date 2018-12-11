package com.niit.service.cms.service;

import com.niit.service.cms.pojo.SkArticleCategoryCn;

import java.util.List;
import java.util.Map;

/**
 * @Description ArticleCategoryCnService服务
 * @Author liyuhao
 * @Date 2018/10/30 20:01
 **/
public interface SkArticleCategoryCnService {
    Map<Integer, List<SkArticleCategoryCn>> getParentList(List<Integer> categoryIds, Integer channelId, String locale);


    int deleteByPrimaryKey(String id);

    int insert(SkArticleCategoryCn record,String locale);

    int insertSelective(SkArticleCategoryCn record);

    SkArticleCategoryCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkArticleCategoryCn record,String locale);

    int updateByPrimaryKey(SkArticleCategoryCn record);

    // 批量删除栏目类别
    int deleteMoreArticleCategoryCn(String id);

    // 查询栏目类别节点树，查询所有栏目类别(包含层级关系)
    List<SkArticleCategoryCn> getNodeTree(Integer channelId,String locale);

    // 根据ChannelId获取特定栏目类别
    List<SkArticleCategoryCn> selectArticleCategoryByChannelId(Integer channelId);

    // 查询父级栏目目录，用于递归
    List<String> getBaseCategory(String id);

    // 根据传来的ID，删除此ID和此ID下的子孙ID栏目类别
    int deleteAllCategoryCn(String id,String locale);

    // 批量修改sortId
    int updateMoreSortId(List<SkArticleCategoryCn> skArticleCategoryCnList,String locale);

    // 根据父类id查询对应的子类
    List<SkArticleCategoryCn> getNextInfos(Integer parentId);

    // 查询所有
    List<SkArticleCategoryCn> selectAllCategory();

    // 查询channel为1的，parent_id为0的栏目类别
    List<SkArticleCategoryCn> selectNewsCategory(String locale);


}

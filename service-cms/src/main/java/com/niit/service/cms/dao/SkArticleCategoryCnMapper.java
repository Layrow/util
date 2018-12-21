package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkArticleCategoryCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkArticleCategoryCnMapper {
    List<SkArticleCategoryCn> getMainCategory(Integer channelId);

    int deleteByPrimaryKey(String id);

    int insert(SkArticleCategoryCn record);

    int insertSelective(SkArticleCategoryCn record);

    SkArticleCategoryCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkArticleCategoryCn record);

    int updateByPrimaryKey(SkArticleCategoryCn record);

    // 批量删除ArticleCategoryCn
    int deleteMoreArticleCategoryCn(List<String> list);

    // 获取栏目类别节点树，用于查询栏目显示层次结构
    List<SkArticleCategoryCn> getNodeTree(Integer channelId);

    // 根据ChannelId获取特定栏目类别
    List<SkArticleCategoryCn> selectArticleCategoryByChannelId(Integer channelId);

    // 根据特定id删除id下的子栏目
    int deleteChildrenByPrimaryKey(String id);

    // 查询一级栏目类别
    List<String> getBaseCategory(String id);


    /**
     * @param
     * @Description 查找所有的Category By channelId
     * @author yuwentao
     */
    List<SkArticleCategoryCn> selectAll(Integer channelId);


    int updateMoreSortId(List<SkArticleCategoryCn> skArticleCategoryCnList);

    // 根据父类id查询对应的子类
    List<SkArticleCategoryCn> getNextInfos(Integer parentId);

    // 查询所有
    List<SkArticleCategoryCn> selectAllCategory();
	
	// 查询channel为1的，parent_id为0的栏目类别
    List<SkArticleCategoryCn> selectCategory(Integer channelId);

    // 级联删除与栏目相关的内容
    Integer deleteAboutArticleCategoryCn(List<String> list);



}
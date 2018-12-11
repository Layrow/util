package com.niit.service.bbs.dao;

import com.niit.service.bbs.pojo.SkBbsSection;

import java.util.List;

public interface SkBbsSectionMapper {

    /**
     * 根据栏目 Id 获取栏目名称
     * @param id
     * @return
     */
   String selectNameForEdit(Integer id);

    /**
     * 根据栏目主键删除栏目
     * @param id  栏目主键
     * @return
     */
     int deleteByPrimaryKey(List<String> id);

    int insert(SkBbsSection record);
    /**
     * 分页模糊查询
     * @param name
     * @return
     */
    List<SkBbsSection> likeSelectAll(String name);
    /**
     * 新增栏目
     * @param record 栏目信息
     * @return
     */
    int insertSelective(SkBbsSection record);

    SkBbsSection selectByPrimaryKey(Integer id);

    /**
     * 查询所有的栏目,按照排序显示
     * @return 所有的栏目集合
     */
    List<SkBbsSection> doSelectAllInOrder();

    /**
     * 批量修改栏目的排序值
     * @param list 传入的需要修改的对象集合
     * @return 返回修改行数
     */
    int doBatchUpdateSectionOrder(List<SkBbsSection> list);

    /**
     * 修改单个 栏目信息
     * @param record  单个栏目对象
     * @return
     */
    int updateByPrimaryKeySelective(SkBbsSection record);

    int updateByPrimaryKey(SkBbsSection record);
}
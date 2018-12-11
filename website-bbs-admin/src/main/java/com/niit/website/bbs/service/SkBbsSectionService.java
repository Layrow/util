package com.niit.website.bbs.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.bbs.pojo.SkBbsSection;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/6
 * @since 1.0.0
 */
public interface SkBbsSectionService {

    String getName(Integer sectionId);
    /**
     * 增加 模块功能
     * @param section
     * @return
     */
    boolean addSection(SkBbsSection section);

    /**
     * 删除栏目,根据主键
     * @param id
     * @return
     */
    boolean deleteSection(String id);

    /**
     * 批量跟新 栏目排序值
     * @param list
     * @return
     */
    boolean updateOderBatch(List<SkBbsSection> list);

    /**
     * 查询所有的栏目,按照排序值升序排列
     * @return 栏目对象排序后的
     */
    List<SkBbsSection> selectAllSection();

    /**
     * 修改 栏目信息
     * @param section
     * @return
     */
    boolean editSection(SkBbsSection section);
    /**
     * 分页模糊查询
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    PageInfo<SkBbsSection> likeSelectAll(Integer currentPage, Integer pageSize, String name);


}

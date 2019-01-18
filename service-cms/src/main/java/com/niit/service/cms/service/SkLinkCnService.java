package com.niit.service.cms.service;

import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkLinkCn;

import java.util.List;

public interface SkLinkCnService {
    //插入单条链接
    int insert(SkLinkCn record, String locale);

    //更新单条链接
    int updateByPrimaryKey(SkLinkCn record, String locale);

    //分页查询链接
    PageInfo<SkLinkCn> selectByPage(int currentPage, int pageSize, String locale);

    //批量更新多条链接
    Integer updateByList(List<SkLinkCn> record, String locale);


    /**
     * 实现链接的批量删除 处理
     *
     * @param id     要删除的 数据集合
     * @param locale 选择的语言
     * @return 如果集合为null 或者 长度为0,删除失败都会返回false
     */
    boolean deleteBatchLinkByPromaryKey(String id, String locale);

    /**
     * 实现链接的批量审核处理
     *
     * @param id     要审核的 数据集合
     * @param locale 选择的语言
     * @return 如果集合为null 或者 长度为0,删除失败都会返回false
     */
    boolean check(String id, String locale);

    /**
     * 分页模糊查询
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param title       模糊查询关键字
     * @param locale      选择语言
     * @return 查询结果集
     */
    PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize, String title, String locale);

    /**
     * 分页查询全部
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param locale      文字
     * @return 返回的页面内容
     */
    PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize, String locale);
}

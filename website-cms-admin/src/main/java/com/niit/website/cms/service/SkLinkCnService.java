package com.niit.website.cms.service;


import com.github.pagehelper.PageInfo;
import com.niit.website.cms.pojo.SkLinkCn;

import java.util.List;

public interface SkLinkCnService {
    //插入单条链接
    int insert(SkLinkCn record,String locale);
    PageInfo<SkLinkCn> selectByPage(int currentPage, int pageSize,String locale);

    //更新单条链接
    int updateByPrimaryKey(SkLinkCn record,String locale);
    //更新多个链接
    int updateList (List<SkLinkCn> list,String locale);

    //批量更新多条链接
    Integer updateByList(List<SkLinkCn> record, String locale);
    /**
     * 实现链接的批量删除 处理
     * @param ids 要删除的 数据集合
     * @return 如果集合为null 或者 长度为0,删除失败都会返回false
     */
    boolean deleteBatchLinkByPromaryKey(String ids,String locale);
    /**
     * 实现链接的批量审核处理
     * @param ids 要审核的 数据集合
     * @return 如果集合为null 或者 长度为0,删除失败都会返回false
     */
    boolean check(String ids,String locale);

    /**
     * 模糊查询实现
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param title 模糊关键字
     * @param locale 语言
     * @return
     */
    PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize,String title,String locale);

    /**
     * 查询所有的连接
     * @param currentPage
     * @param pageSize
     * @param locale
     * @return
     */
    PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize,String locale);
}

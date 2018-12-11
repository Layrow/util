package com.niit.service.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.cms.dao.SkLinkCnMapper;
import com.niit.service.cms.dao.SkLinkEnMapper;
import com.niit.service.cms.pojo.SkLinkCn;
import com.niit.service.cms.service.SkLinkCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class SkLinkCnServiceImpl implements SkLinkCnService {

    @Autowired
    SkLinkCnMapper skLinkCnMapper;
    @Autowired
    SkLinkEnMapper skLinkEnMapper;

    public Object getMapper(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", skLinkCnMapper);
        foo.put("en", skLinkEnMapper);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : skLinkCnMapper;
        return mapper;
    }

    @Override
    public Integer updateByList(List<SkLinkCn> record, String locale) {
        Object mapper=getMapper(locale);
        try {
            Method method = mapper.getClass().getMethod("updateByList", List.class);
            return (Integer) method.invoke(mapper,record);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int insert(SkLinkCn record, String locale) {
        switch (locale) {
            case "zh":
                return skLinkCnMapper.insert(record);
            case "en":
                return skLinkEnMapper.insert(record);
            default:
                return skLinkCnMapper.insert(record);
        }
    }

    @Override
    public int updateByPrimaryKey(SkLinkCn record,String locale) {
        switch (locale) {
            case "zh":
                return skLinkCnMapper.updateByPrimaryKey(record);
            case "en":
                return skLinkEnMapper.updateByPrimaryKey(record);
            default:
                return skLinkCnMapper.updateByPrimaryKey(record);
        }
    }

    /**
     * @param currentPage 当前页数
     * @param pageSize    单页数据数
     * @Description 分页查询
     * @author yuwentao
     */
    @Override
    public PageInfo<SkLinkCn> selectByPage(int currentPage, int pageSize,String locale) {
        List<SkLinkCn> list;
        PageInfo<SkLinkCn> listInfo;
        switch (locale) {
            case "zh":
                //开启分页
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                //开启分页
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkEnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                //开启分页
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }


    /**
     * 删除操作
     *
     * @param ids 要删除的 数据集合
     * @return
     */
    @Override
    public boolean deleteBatchLinkByPromaryKey(String ids,String locale) {
        if (ids == null||"".equals(ids)) {
            return false;
        }
        switch (locale) {
            case "zh":
                return this.skLinkCnMapper.deleteBatchByPrimaryKey(Tools.getList(ids));
            case "en":
                return this.skLinkEnMapper.deleteBatchByPrimaryKey(Tools.getList(ids));
            default:
                return this.skLinkCnMapper.deleteBatchByPrimaryKey(Tools.getList(ids));
        }
    }

    /**
     * 审核操作
     *
     * @param ids 要审核的 数据集合
     * @return
     */
    @Override
    public boolean check(String ids,String locale) {
        if (ids == null || "".equals(ids)) {
            return false;
        }
        switch (locale) {
            case "zh":
                return this.skLinkCnMapper.doCheck(Tools.getList(ids));
            case "en":
                return this.skLinkEnMapper.doCheck(Tools.getList(ids));
            default:
                return this.skLinkCnMapper.doCheck(Tools.getList(ids));
        }
    }

    /**
     * 分页模糊查询
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param title       模糊查询关键字
     * @param locale      选择语言
     * @return 查询结果集
     */
    @Override
    public PageInfo<SkLinkCn> likeSelectAll(int currentPage, int pageSize, String title, String locale) {
        List<SkLinkCn> list;
        PageInfo<SkLinkCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkEnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }

    /**
     * 分页查询全部
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param locale      文字
     * @return 返回的页面内容
     */
    @Override
    public PageInfo<SkLinkCn> listAllLink(int currentPage, int pageSize, String locale) {
        List<SkLinkCn> list;
        PageInfo<SkLinkCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.listAllLink();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.listAllLink();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skLinkCnMapper.listAllLink();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }
}

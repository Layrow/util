package com.niit.service.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.dao.SkArticleCategoryCnMapper;
import com.niit.service.cms.dao.SkArticleCategoryEnMapper;
import com.niit.service.cms.dao.SkChannelArticleVideoCnMapper;
import com.niit.service.cms.dao.SkChannelArticleVideoEnMapper;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import com.niit.service.cms.pojo.SkChannelArticleVideoCn;
import com.niit.service.cms.service.SkChannelArticleVideoCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkChannelArticleVideoCnServiceImpl implements SkChannelArticleVideoCnService {
    @Autowired
    private SkChannelArticleVideoCnMapper skChannelArticleVideoCnMapper;
    @Autowired
    private SkChannelArticleVideoEnMapper skChannelArticleVideoEnMapper;
    @Autowired
    private SkArticleCategoryCnMapper cc;
    @Autowired
    private SkArticleCategoryEnMapper ce;

    public Object getMapper(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", skChannelArticleVideoCnMapper);
        foo.put("en", skChannelArticleVideoEnMapper);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : skChannelArticleVideoCnMapper;
        return mapper;
    }

    public Object getMapper2(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", cc);
        foo.put("en", ce);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : cc;
        return mapper;
    }

    /**
     * @param
     * @Description getMapper函数拿到对应语言的mapper, 利用反射调用方法，若要增加语言，无需改动service层
     * @author yuwentao
     */
    //分页模糊查询
    @Override
    public PageInfo<SkChannelArticleVideoCn> likeSelectAll(int currentPage, int pageSize, String title, String locale, @Param(value = "categoryId") Integer categoryId, String key) {
        PageInfo<SkChannelArticleVideoCn> listInfo = null;
        List<Integer> categoryIdList = new LinkedList<>();
        Object mapper = getMapper(locale);
        List<SkArticleCategoryCn> totalList = null;
        try {
            totalList = (List<SkArticleCategoryCn>) getMapper2(locale).getClass().getMethod("selectAll", Integer.class).invoke(getMapper2(locale), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SkArticleCategoryCn> pList;
        if (categoryId == 0) {
            //categoryId=0,则去找parentID()=0的数据
            pList = totalList.stream().filter(e -> e.getParentId().equals(0)).collect(Collectors.toList());
        } else {
            pList = totalList.stream().filter(e -> e.getId().equals(categoryId)).collect(Collectors.toList());
        }
        //递归拿到指定categoryId的儿子栏目的id集合
        categoryIdList = test(totalList, pList, categoryIdList);
        //数据库查询
        if (categoryIdList != null && categoryIdList.size() > 0) {
            try {
                PageHelper.startPage(currentPage, pageSize);
                Method method = mapper.getClass().getMethod("likeSelectAllByCategoryId", String.class, String.class, List.class);
                List<SkChannelArticleVideoCn> list = (List<SkChannelArticleVideoCn>) method.invoke(mapper, title, key, categoryIdList);
                listInfo = new PageInfo<>(list);
            } catch (Exception e) {
                e.printStackTrace();
                return listInfo;
            }
        }
        return listInfo;
    }

    //递归拿到指定categoryId的儿子栏目的id集合
    public List<Integer> test(List<SkArticleCategoryCn> totalList, List<SkArticleCategoryCn> pList, List<Integer> categoryIdList) {
        pList.stream().forEach(e -> {
            categoryIdList.add(e.getId());
            test(totalList, totalList.stream().filter(p -> p.getParentId().equals(e.getId())).collect(Collectors.toList()), categoryIdList);
        });
        return categoryIdList;
    }

    @Override
    public int deleteByPrimaryKey(Integer id, String locale) {
        return skChannelArticleVideoCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkChannelArticleVideoCn record, String locale) {
        return skChannelArticleVideoCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkChannelArticleVideoCn record, String locale) {

        switch (locale) {
            case "zh":
                return skChannelArticleVideoCnMapper.insertSelective(record);
            case "en":
                return skChannelArticleVideoEnMapper.insertSelective(record);
            default:
                return skChannelArticleVideoCnMapper.insertSelective(record);
        }
    }

    @Override
    public int batchUp(List<SkChannelArticleVideoCn> lis, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleVideoCnMapper.batchUp(lis);
            case "en":
                return skChannelArticleVideoEnMapper.batchUp(lis);
            default:
                return skChannelArticleVideoCnMapper.batchUp(lis);
        }
    }

    @Override
    public void updateTo(String id, String locale) {

        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleVideoCnMapper.updateTo(list);
            case "en":
                skChannelArticleVideoEnMapper.updateTo(list);
            default:
                skChannelArticleVideoCnMapper.updateTo(list);
        }

    }

    @Override
    public void updateRe(String id, String locale) {

        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleVideoCnMapper.updateRe(list);
            case "en":
                skChannelArticleVideoEnMapper.updateRe(list);
            default:
                skChannelArticleVideoCnMapper.updateRe(list);
        }

    }

    @Override
    public void updateHo(String id, String locale) {

        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleVideoCnMapper.updateHo(list);
            case "en":
                skChannelArticleVideoEnMapper.updateHo(list);
            default:
                skChannelArticleVideoCnMapper.updateHo(list);
        }

    }

    @Override
    public String selectVideoByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageInfo<SkChannelArticleVideoCn> listInfo = null;
        // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在set中 --en
        // 递归实现
        List<String> addTimeList = new ArrayList<>();
        List<String> cnList = new ArrayList<>();
        List<String> enListID = new ArrayList<>();
        getAllChildrenCategoryCn(Integer.toString(categoryId), cnList);
        getAllChildrenCategoryEn(Integer.toString(categoryId), enListID);
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleVideoCn> newsCnList = skChannelArticleVideoCnMapper.selectVideoByCategoryId(cnList);
                listInfo = new PageInfo<>(newsCnList);
                listInfo.getList().forEach(x -> {
                    String s = null;
                    if (x.getAddTime() != null) {
                        Date time = x.getAddTime();
                        SimpleDateFormat sdfDay = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        s = sdfDay.format(time);
                    }
                    addTimeList.add(s);
                });
                map.put("listInfo", listInfo);
                map.put("addTimeList", addTimeList);
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
                return gson.toJson(map);
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleVideoCn> newsEnList = skChannelArticleVideoEnMapper.selectVideoByCategoryId(enListID);
                listInfo = new PageInfo<>(newsEnList);
                listInfo.getList().forEach(x -> {
                    String s = null;
                    if (x.getAddTime() != null) {
                        Date time = x.getAddTime();
                        SimpleDateFormat sdfDay = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        s = sdfDay.format(time);
                    }
                    addTimeList.add(s);
                });
                map.put("listInfo", listInfo);
                map.put("addTimeList", addTimeList);
                Gson gsonEn = new GsonBuilder().enableComplexMapKeySerialization().create();
                return gsonEn.toJson(map);
            default:
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleVideoCn> newsList = skChannelArticleVideoEnMapper.selectVideoByCategoryId(cnList);
                listInfo = new PageInfo<>(newsList);
                listInfo.getList().forEach(x -> {
                    String s = null;
                    if (x.getAddTime() != null) {
                        Date time = x.getAddTime();
                        SimpleDateFormat sdfDay = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        s = sdfDay.format(time);
                    }
                    addTimeList.add(s);
                });
                map.put("listInfo", listInfo);
                map.put("addTimeList", addTimeList);
                Gson gsonCn = new GsonBuilder().enableComplexMapKeySerialization().create();
                return gsonCn.toJson(map);
        }
    }

    // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在list中 --zh
    // 递归实现
    public void getAllChildrenCategoryCn(String id, List<String> categoryList) {
        categoryList.add(id);
        List<String> baseCategory = cc.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categoryList.add(x);
            getAllChildrenCategoryCn(x, categoryList);
        });
    }

    // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在list中 --en
    // 递归实现
    public void getAllChildrenCategoryEn(String id, List<String> categoryList) {
        categoryList.add(id);
        List<String> baseCategory = ce.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categoryList.add(x);
            getAllChildrenCategoryEn(x, categoryList);
        });
    }


    @Override
    public SkChannelArticleVideoCn selectByPrimaryKey(Integer id, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleVideoCnMapper.selectByPrimaryKey(id);
            case "en":
                return skChannelArticleVideoEnMapper.selectByPrimaryKey(id);
            default:
                return skChannelArticleVideoCnMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleVideoCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleVideoCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skChannelArticleVideoEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skChannelArticleVideoCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SkChannelArticleVideoCn record, String locale) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SkChannelArticleVideoCn record, String locale) {
        return skChannelArticleVideoCnMapper.updateByPrimaryKey(record);
    }

    //分页查找
    @Override
    public PageInfo<SkChannelArticleVideoCn> selectAll(int currentPage, int pageSize, String locale) {
        List<SkChannelArticleVideoCn> list;
        PageInfo<SkChannelArticleVideoCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoEnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }

    //批量删除
    @Override
    public void deleteAd(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleVideoCnMapper.deleteAd(list);
            case "en":
                skChannelArticleVideoEnMapper.deleteAd(list);
            default:
                skChannelArticleVideoCnMapper.deleteAd(list);
        }
    }

    //批量审核
    @Override
    public void updateSt(String id, String locale) {

        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleVideoCnMapper.updateSt(list);
            case "en":
                skChannelArticleVideoEnMapper.updateSt(list);
            default:
                skChannelArticleVideoCnMapper.updateSt(list);
        }

    }

    //将ID放入List
    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;

    }

    //分页模糊查询
    @Override
    public PageInfo<SkChannelArticleVideoCn> likeSelectAll(int currentPage, int pageSize, String title, String locale) {
        List<SkChannelArticleVideoCn> list;
        PageInfo<SkChannelArticleVideoCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoEnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleVideoCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }


    //分页查询所有
    @Override
    public PageInfo<SkChannelArticleVideoCn> FuzzySearchBy(String key, int currentPage, int pageSize, String locale) {
        List<SkChannelArticleVideoCn> list = null;
        PageInfo<SkChannelArticleVideoCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleVideoCnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleVideoCnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleVideoCnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleVideoCnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleVideoCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleVideoCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleVideoEnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleVideoEnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleVideoEnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleVideoEnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleVideoEnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleVideoEnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleVideoCnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleVideoCnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleVideoCnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleVideoCnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleVideoCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleVideoCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }
}

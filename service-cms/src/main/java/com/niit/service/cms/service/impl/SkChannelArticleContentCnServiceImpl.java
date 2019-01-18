package com.niit.service.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.dao.SkArticleCategoryCnMapper;
import com.niit.service.cms.dao.SkArticleCategoryEnMapper;
import com.niit.service.cms.dao.SkChannelArticleContentCnMapper;
import com.niit.service.cms.dao.SkChannelArticleContentEnMapper;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.pojo.SkChannelArticleContentCn;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import com.niit.service.cms.service.SkChannelArticleContentCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkChannelArticleContentCnServiceImpl implements SkChannelArticleContentCnService {
    @Autowired
    private SkChannelArticleContentEnMapper skChannelArticleContentEnMapper;
    @Autowired
    private SkChannelArticleContentCnMapper skChannelArticleContentCnMapper;
    @Autowired
    private SkArticleCategoryCnMapper cc;
    @Autowired
    private SkArticleCategoryEnMapper ce;

    public Object getMapper(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", skChannelArticleContentCnMapper);
        foo.put("en", skChannelArticleContentEnMapper);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : skChannelArticleContentCnMapper;
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
    public PageInfo<SkChannelArticleContentCn> likeSelectAll(int currentPage, int pageSize, String title, String locale, @Param(value = "categoryId") Integer categoryId, String key) {
        PageInfo<SkChannelArticleContentCn> listInfo = null;
        List<Integer> categoryIdList = new LinkedList<>();
        Object mapper = getMapper(locale);
        List<SkArticleCategoryCn> totalList = null;
        try {
            totalList = (List<SkArticleCategoryCn>) getMapper2(locale).getClass().getMethod("selectAll", Integer.class).invoke(getMapper2(locale), 4);
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
                List<SkChannelArticleContentCn> list = (List<SkChannelArticleContentCn>) method.invoke(mapper, title, key, categoryIdList);
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

    //插入保存
    @Override
    public int insert(SkChannelArticleContentCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.insert(record);
            case "en":
                return skChannelArticleContentEnMapper.insert(record);
            default:
                return skChannelArticleContentCnMapper.insert(record);
        }
    }

    @Override
    public int batchUp(List<SkChannelArticleContentCn> lis, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.batchUp(lis);
            case "en":
                return skChannelArticleContentEnMapper.batchUp(lis);
            default:
                return skChannelArticleContentCnMapper.batchUp(lis);
        }
    }

    @Override
    public String selectContentByCategoryId(String locale, Integer categoryId, Integer currentPage, Integer pageSize) {
        // 按照栏目类别ID并且status = 1查找新闻
        Map<String, Object> map = new HashMap<>();
        PageInfo<SkChannelArticleContentCn> listInfo = null;
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
                List<SkChannelArticleContentCn> newsCnList = skChannelArticleContentCnMapper.selectContentByCategoryId(cnList);
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
                List<SkChannelArticleContentCn> newsEnList = skChannelArticleContentEnMapper.selectContentByCategoryId(enListID);
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
                List<SkChannelArticleContentCn> newsList = skChannelArticleContentCnMapper.selectContentByCategoryId(cnList);
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

    //部分插入保存 选择性保存数据
    @Override
    public int insertSelective(SkChannelArticleContentCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.insertSelective(record);
            case "en":
                return skChannelArticleContentEnMapper.insertSelective(record);
            default:
                return skChannelArticleContentCnMapper.insertSelective(record);
        }

    }

    //通过主键查找数据
    @Override
    public SkChannelArticleContentCn selectByPrimaryKey(Integer id, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.selectByPrimaryKey(id);
            case "en":
                return skChannelArticleContentEnMapper.selectByPrimaryKey(id);
            default:
                return skChannelArticleContentCnMapper.selectByPrimaryKey(id);
        }
    }

    //通过主键删除
    @Override
    public int deleteByPrimaryKey(Integer id, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.deleteByPrimaryKey(id);
            case "en":
                return skChannelArticleContentEnMapper.deleteByPrimaryKey(id);
            default:
                return skChannelArticleContentCnMapper.deleteByPrimaryKey(id);
        }
    }

    //通过主键更新新pojo不为空的内容
    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleContentCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleContentCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skChannelArticleContentEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skChannelArticleContentCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    //
    @Override
    public int updateByPrimaryKeyWithBLOBs(SkChannelArticleContentCn record, String locale) {
        return skChannelArticleContentCnMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    //将为空的字段在数据库中置为NULL
    @Override
    public int updateByPrimaryKey(SkChannelArticleContentCn record, String locale) {
        return skChannelArticleContentCnMapper.updateByPrimaryKey(record);
    }

    //分页查找
    @Override
    public PageInfo<SkChannelArticleContentCn> selectAll(int currentPage, int pageSize, String locale) {
        List<SkChannelArticleContentCn> list;
        PageInfo<SkChannelArticleContentCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleContentCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleContentEnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleContentCnMapper.selectAll();
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
                skChannelArticleContentCnMapper.deleteAd(list);
            case "en":
                skChannelArticleContentEnMapper.deleteAd(list);
            default:
                skChannelArticleContentCnMapper.deleteAd(list);
        }

    }

    /**
     * 功能描述:批量审核
     *
     * @return void
     * @author huangwei
     * @date 2018/11/1
     * @params [id]
     */
    @Override
    public void updateSt(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleContentCnMapper.updateSt(list);
            case "en":
                skChannelArticleContentEnMapper.updateSt(list);
            default:
                skChannelArticleContentCnMapper.updateSt(list);
        }
    }

    @Override
    public void updateTo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleContentCnMapper.updateTo(list);
            case "en":
                skChannelArticleContentEnMapper.updateTo(list);
            default:
                skChannelArticleContentCnMapper.updateTo(list);
        }
    }

    @Override
    public void updateRe(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleContentCnMapper.updateRe(list);
            case "en":
                skChannelArticleContentEnMapper.updateRe(list);
            default:
                skChannelArticleContentCnMapper.updateRe(list);
        }

    }

    @Override
    public void updateHo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleContentCnMapper.updateHo(list);
            case "en":
                skChannelArticleContentEnMapper.updateHo(list);
            default:
                skChannelArticleContentCnMapper.updateRe(list);
        }

    }

//    /**
//     * 功能描述: 模糊查询进行分页
//     * @author huangwei
//     * @date 2018/11/8
//     * @params [currentPage, pageSize, title]
//     * @return com.github.pagehelper.PageInfo<com.niit.service.cms.pojo.SkChannelArticleContentCn>
//     */
//    @Override
//    public PageInfo<SkChannelArticleContentCn> likeSelectAll(int currentPage, int pageSize, String title,String locale) {
//        List<SkChannelArticleContentCn> list;
//        PageInfo<SkChannelArticleContentCn> listInfo;
//        switch (locale) {
//            case "zh":
//                PageHelper.startPage(currentPage,pageSize);
//                //执行SQL语句（list->分页后的数据）
//                list=skChannelArticleContentCnMapper.likeSelectAll(title);
//                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
//                listInfo = new PageInfo<>(list);
//                return  listInfo;
//            case "en":
//                PageHelper.startPage(currentPage,pageSize);
//                //执行SQL语句（list->分页后的数据）
//                list=skChannelArticleContentEnMapper.likeSelectAll(title);
//                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
//                listInfo = new PageInfo<>(list);
//                return  listInfo;
//            default:
//                PageHelper.startPage(currentPage,pageSize);
//                //执行SQL语句（list->分页后的数据）
//                list=skChannelArticleContentCnMapper.likeSelectAll(title);
//                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
//                listInfo = new PageInfo<>(list);
//                return  listInfo;
//        }
//    }

    /**
     * id放入list
     *
     * @param id
     * @return
     */

    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;

    }


    //分页查询所有
    @Override
    public PageInfo<SkChannelArticleContentCn> FuzzySearchBy(String key, int currentPage, int pageSize, String locale) {
        List<SkChannelArticleContentCn> list = null;
        PageInfo<SkChannelArticleContentCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleContentCnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleContentCnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleContentCnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleContentCnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleContentCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleContentCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleContentEnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleContentEnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleContentEnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleContentEnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleContentEnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleContentEnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                if (key.equals("all")) list = skChannelArticleContentCnMapper.selectAll();
                else if (key.equals("red")) list = skChannelArticleContentCnMapper.selectAllByRed();
                else if (key.equals("hot")) list = skChannelArticleContentCnMapper.selectAllByHot();
                else if (key.equals("top")) list = skChannelArticleContentCnMapper.selectAllByTop();
                else if (key.equals("audited")) list = skChannelArticleContentCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list = skChannelArticleContentCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }


}

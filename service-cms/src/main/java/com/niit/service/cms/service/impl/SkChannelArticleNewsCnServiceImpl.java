package com.niit.service.cms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.service.cms.dao.SkArticleCategoryCnMapper;
import com.niit.service.cms.dao.SkArticleCategoryEnMapper;
import com.niit.service.cms.dao.SkChannelArticleNewsCnMapper;
import com.niit.service.cms.dao.SkChannelArticleNewsEnMapper;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import com.niit.service.cms.service.SkChannelArticleNewsCnService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkChannelArticleNewsCnServiceImpl implements SkChannelArticleNewsCnService{

    @Autowired
    private SkArticleCategoryCnMapper skArticleCategoryCnMapper;
    @Autowired
    private SkArticleCategoryCnMapper skArticleCategoryEnMapper;
    @Autowired
    private SkChannelArticleNewsCnMapper skChannelArticleNewsCnMapper ;
    @Autowired
    private SkChannelArticleNewsEnMapper skChannelArticleNewsEnMapper;
    @Autowired
    private SkArticleCategoryCnMapper cc;
    @Autowired
    private SkArticleCategoryEnMapper ce;

    public Object getMapper(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", skChannelArticleNewsCnMapper);
        foo.put("en", skChannelArticleNewsEnMapper);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : skChannelArticleNewsCnMapper;
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

    // 按照栏目类别ID并且status = 1查找新闻
    @Override
    public String selectNewsByCategoryId(String locale,Integer categoryId,Integer currentPage,Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        PageInfo<SkChannelArticleNewsCn> listInfo=null;
        // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在set中 --en
        // 递归实现
        List<String> addTimeList = new ArrayList<>();
        List<String> cnList = new ArrayList<>();
        List<String> enListID = new ArrayList<>();
        getAllChildrenCategoryCn(Integer.toString(categoryId),cnList);
        getAllChildrenCategoryEn(Integer.toString(categoryId),enListID);
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleNewsCn> newsCnList = skChannelArticleNewsCnMapper.selectNewsByCategoryId(cnList);
                listInfo = new PageInfo<>(newsCnList);
                listInfo.getList().forEach(x -> {
                    Date time = x.getAddTime();
                    SimpleDateFormat sdfDay = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    String s = sdfDay.format(time);
                    addTimeList.add(s);
                });
                map.put("listInfo", listInfo);
                map.put("addTimeList", addTimeList);
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
                return gson.toJson(map);
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleNewsCn> newsEnList = skChannelArticleNewsEnMapper.selectNewsByCategoryId(enListID);
                listInfo = new PageInfo<>(newsEnList);
                listInfo.getList().forEach(x -> {
                    Date time = x.getAddTime();
                    SimpleDateFormat sdfDay = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    String s = sdfDay.format(time);
                    addTimeList.add(s);
                });
                map.put("listInfo", listInfo);
                map.put("addTimeList", addTimeList);
                Gson gsonEn = new GsonBuilder().enableComplexMapKeySerialization().create();
                return gsonEn.toJson(map);
            default:
                PageHelper.startPage(currentPage, pageSize);
                List<SkChannelArticleNewsCn> newsList = skChannelArticleNewsCnMapper.selectNewsByCategoryId(cnList);
                listInfo = new PageInfo<>(newsList);
                listInfo.getList().forEach(x -> {
                    Date time = x.getAddTime();
                    SimpleDateFormat sdfDay = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    String s = sdfDay.format(time);
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
    public void getAllChildrenCategoryCn(String id,List<String> categoryList) {
        categoryList.add(id);
        List<String> baseCategory = skArticleCategoryCnMapper.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categoryList.add(x);
            getAllChildrenCategoryCn(x,categoryList);
        });
    }

    // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在list中 --en
    // 递归实现
    public void getAllChildrenCategoryEn(String id,List<String> categoryList) {
        categoryList.add(id);
        List<String> baseCategory = skArticleCategoryEnMapper.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categoryList.add(x);
            getAllChildrenCategoryEn(x,categoryList);
        });
    }

    /**
     * @param
     * @Description getMapper函数拿到对应语言的mapper, 利用反射调用方法，若要增加语言，无需改动service层
     * @author yuwentao
     */
    //分页模糊查询
    @Override
    public PageInfo<SkChannelArticleNewsCn> likeSelectAll(int currentPage, int pageSize, String title, String locale, @Param(value = "categoryId") Integer categoryId, String key) {
        PageInfo<SkChannelArticleNewsCn> listInfo=null;
        List<Integer> categoryIdList=new LinkedList<>();
        Object mapper = getMapper(locale);
        List<SkArticleCategoryCn> totalList=null;
        try {
            totalList= (List<SkArticleCategoryCn>) getMapper2(locale).getClass().getMethod("selectAll",Integer.class).invoke(getMapper2(locale),1);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<SkArticleCategoryCn> pList;
        if (categoryId==0){
            //categoryId=0,则去找parentID()=0的数据
            pList=totalList.stream().filter(e->e.getParentId().equals(0)).collect(Collectors.toList());
        } else {
            pList= totalList.stream().filter(e -> e.getId().equals(categoryId)).collect(Collectors.toList());
        }
        //递归拿到指定categoryId的儿子栏目的id集合
        categoryIdList=test(totalList,pList,categoryIdList);
        //数据库查询
        if (categoryIdList!=null&&categoryIdList.size()>0 ) {
            try {
                PageHelper.startPage(currentPage, pageSize);
                Method method = mapper.getClass().getMethod("likeSelectAllByCategoryId", String.class, String.class, List.class);
                List<SkChannelArticleNewsCn> list = (List<SkChannelArticleNewsCn>) method.invoke(mapper, title, key, categoryIdList);
                listInfo = new PageInfo<>(list);
            } catch (Exception e) {
                e.printStackTrace();
                return listInfo;
            }
        }
        return listInfo;
    }

    //递归拿到指定categoryId的儿子栏目的id集合
    public List<Integer> test(List<SkArticleCategoryCn> totalList, List<SkArticleCategoryCn> pList, List<Integer> categoryIdList){
        pList.stream().forEach(e->{
            categoryIdList.add(e.getId());
            test(totalList,totalList.stream().filter(p->p.getParentId().equals(e.getId())).collect(Collectors.toList()),categoryIdList);
        });
        return categoryIdList;
    }

    @Override
    public int deleteByPrimaryKey(Integer id,String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.deleteByPrimaryKey(id);
            case "en":
                return skChannelArticleNewsEnMapper.deleteByPrimaryKey(id);
            default:
                return skChannelArticleNewsCnMapper.deleteByPrimaryKey(id);
        }
    }
    @Override
    public int batchUp(List<SkChannelArticleNewsCn> lis,String locale) {
        switch (locale) {
            case "zh":
                return  skChannelArticleNewsCnMapper.batchUp(lis);
            case "en":
                return  skChannelArticleNewsEnMapper.batchUp(lis);
            default:
                return  skChannelArticleNewsCnMapper.batchUp(lis);
        }
    }

    @Override
    public void updateTo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleNewsCnMapper.updateTo(list);
            case "en":
                skChannelArticleNewsEnMapper.updateTo(list);
            default:
                skChannelArticleNewsCnMapper.updateTo(list);
        }

    }

    @Override
    public void updateRe(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleNewsCnMapper.updateRe(list);
            case "en":
                skChannelArticleNewsEnMapper.updateRe(list);
            default:
                skChannelArticleNewsCnMapper.updateRe(list);
        }

    }

    @Override
    public void updateHo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleNewsCnMapper.updateHo(list);
            case "en":
                skChannelArticleNewsEnMapper.updateHo(list);
            default:
                skChannelArticleNewsCnMapper.updateHo(list);
        }

    }

    @Override
    public int insert(SkChannelArticleNewsCn record,String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.insert(record);
            case "en":
                return skChannelArticleNewsEnMapper.insert(record);
            default:
                return skChannelArticleNewsCnMapper.insert(record);
        }
    }

    @Override
    public int insertSelective(SkChannelArticleNewsCn record,String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.insertSelective(record);
            case "en":
                return skChannelArticleNewsEnMapper.insertSelective(record);
            default:
                return skChannelArticleNewsCnMapper.insertSelective(record);
        }
    }

    @Override
    public SkChannelArticleNewsCn selectByPrimaryKey(Integer id,String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.selectByPrimaryKey(id);
            case "en":
                return skChannelArticleNewsEnMapper.selectByPrimaryKey(id);
            default:
                return skChannelArticleNewsCnMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleNewsCn record,String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skChannelArticleNewsEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skChannelArticleNewsCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SkChannelArticleNewsCn record,String locale) {
         return  1;
    }

    @Override
    public int updateByPrimaryKey(SkChannelArticleNewsCn record,String locale) {
        record.setUpdateTime(new Date());
        switch (locale) {
            case "zh":
                return skChannelArticleNewsCnMapper.updateByPrimaryKey(record);
            case "en":
                return skChannelArticleNewsEnMapper.updateByPrimaryKey(record);
            default:
                return skChannelArticleNewsCnMapper.updateByPrimaryKey(record);
        }
    }
    //分页查找
    @Override
    public PageInfo<SkChannelArticleNewsCn> selectAll(int currentPage, int pageSize,String locale) {
        List<SkChannelArticleNewsCn> list;
        PageInfo<SkChannelArticleNewsCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            case "en":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsEnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            default:
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
        }
    }



    //批量删除
    @Override
    public void deleteAd(String id,String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleNewsCnMapper.deleteAd(list);
            case "en":
                skChannelArticleNewsEnMapper.deleteAd(list);
            default:
                skChannelArticleNewsCnMapper.deleteAd(list);
        }
    }

    //批量审核
    @Override
    public void updateSt(String id,String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleNewsCnMapper.updateSt(list);
            case "en":
                skChannelArticleNewsEnMapper.updateSt(list);
            default:
                skChannelArticleNewsCnMapper.updateSt(list);
        }
    }
    /**
     * id放入list
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

    //分页模糊查询
    @Override
    public PageInfo<SkChannelArticleNewsCn> likeSelectAll(int currentPage, int pageSize, String title,String locale) {
        List<SkChannelArticleNewsCn> list;
        PageInfo<SkChannelArticleNewsCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            case "en":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsEnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            default:
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                list=skChannelArticleNewsCnMapper.likeSelectAll(title);
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
        }
    }

    //分页查询所有
    @Override
    public PageInfo<SkChannelArticleNewsCn> FuzzySearchBy(String key,int currentPage, int pageSize,String locale) {
        List<SkChannelArticleNewsCn> list=null;
        PageInfo<SkChannelArticleNewsCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                if(key.equals("all")) list = skChannelArticleNewsCnMapper.selectAll();
                else if (key.equals("red")) list=skChannelArticleNewsCnMapper.selectAllByRed();
                else if (key.equals("hot")) list=skChannelArticleNewsCnMapper.selectAllByHot();
                else if (key.equals("top")) list=skChannelArticleNewsCnMapper.selectAllByTop();
                else if (key.equals("audited")) list=skChannelArticleNewsCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list=skChannelArticleNewsCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            case "en":
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                if(key.equals("all")) list = skChannelArticleNewsEnMapper.selectAll();
                else if (key.equals("red")) list=skChannelArticleNewsEnMapper.selectAllByRed();
                else if (key.equals("hot")) list=skChannelArticleNewsEnMapper.selectAllByHot();
                else if (key.equals("top")) list=skChannelArticleNewsEnMapper.selectAllByTop();
                else if (key.equals("audited")) list=skChannelArticleNewsEnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list=skChannelArticleNewsEnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
            default:
                PageHelper.startPage(currentPage,pageSize);
                //执行SQL语句（list->分页后的数据）
                if(key.equals("all")) list = skChannelArticleNewsCnMapper.selectAll();
                else if (key.equals("red")) list=skChannelArticleNewsCnMapper.selectAllByRed();
                else if (key.equals("hot")) list=skChannelArticleNewsCnMapper.selectAllByHot();
                else if (key.equals("top")) list=skChannelArticleNewsCnMapper.selectAllByTop();
                else if (key.equals("audited")) list=skChannelArticleNewsCnMapper.selectAllByAudited();
                else if (key.equals("unaudited")) list=skChannelArticleNewsCnMapper.selectAllByUnaudited();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return  listInfo;
        }
    }
}

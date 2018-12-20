package com.niit.service.cms.service.impl;

import com.niit.common.utils.Tools;
import com.niit.service.cms.commonMethod.CommonMethod;
import com.niit.service.cms.dao.SkArticleCategoryCnMapper;
import com.niit.service.cms.dao.SkArticleCategoryEnMapper;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.service.SkArticleCategoryCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SkArticleCategoryCnServiceImpl
 * @Description SkArticleCategoryCnService接口实现类
 * @Author liyuhao
 * @Date 2018/10/3015:10
 **/
@Service
public class SkArticleCategoryCnServiceImpl implements SkArticleCategoryCnService {

    @Autowired
    private SkArticleCategoryCnMapper skArticleCategoryCnMapper;
    @Autowired
    private SkArticleCategoryEnMapper skArticleCategoryEnMapper;

    public Object getCategoryMapper(String local) {
        return Tools.getMapper(local, skArticleCategoryCnMapper, skArticleCategoryEnMapper);
    }

    @Override
    public Map<Integer, List<SkArticleCategoryCn>> getParentList(List<Integer> categoryIds, Integer channelId, String locale) {
        CommonMethod commonMethod = new CommonMethod();

        Map<Integer, List<SkArticleCategoryCn>> categoryListMap = new LinkedHashMap<>();
        Map<Integer, List<SkArticleCategoryCn>> result=new LinkedHashMap<>();
        try {
            Object categoryMapper = getCategoryMapper(locale);
            //查询所有的栏目信息列表
            Method categoryMethod = categoryMapper.getClass().getMethod("selectAll", Integer.class);
            List<SkArticleCategoryCn> categoryList = (List<SkArticleCategoryCn>) categoryMethod.invoke(categoryMapper, channelId);
            //把所有的categoryIds当作
            categoryIds.stream().distinct().forEach(e -> {
                List<SkArticleCategoryCn> parentCategoryList=new LinkedList();
                parentCategoryList.add(categoryList.stream().filter(c -> c.getId().equals(e)).findFirst().get());
                categoryListMap.put(e, parentCategoryList);
            });
            //调用getParentList方法递归拿到指定栏目id对应的父类栏目信息列表
            result = commonMethod.getParentList(categoryIds, categoryListMap, categoryList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return skArticleCategoryCnMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insert(SkArticleCategoryCn record,String locale) {
        switch (locale) {
            case "zh":
                return skArticleCategoryCnMapper.insert(record);
            case "en":
                return skArticleCategoryEnMapper.insert(record);
            default:
                return skArticleCategoryCnMapper.insert(record);
        }
    }


    @Override
    public int insertSelective(SkArticleCategoryCn record) {
        return skArticleCategoryCnMapper.insertSelective(record);
    }

    @Override
    public SkArticleCategoryCn selectByPrimaryKey(Integer id) {
        return skArticleCategoryCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkArticleCategoryCn record,String locale) {
        switch (locale) {
            case "zh":
                return skArticleCategoryCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skArticleCategoryEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skArticleCategoryCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKey(SkArticleCategoryCn record) {
        return skArticleCategoryCnMapper.updateByPrimaryKey(record);
    }


    // 传入字符串分割成list，批量删除ArticleCategoryCn
    @Override
    public int deleteMoreArticleCategoryCn(String id) {
        if ("".equals(id) || id == null) {
          return 0;
        }
        // String --> List
        List<String> list = getList(id);
        return skArticleCategoryCnMapper.deleteMoreArticleCategoryCn(list);
    }


    @Override
    public List<SkArticleCategoryCn> selectArticleCategoryByChannelId(Integer channelId) {
        return skArticleCategoryCnMapper.selectArticleCategoryByChannelId(channelId);
    }

    @Override
    public List<String> getBaseCategory(String id) {
        return skArticleCategoryCnMapper.getBaseCategory(id);
    }

    @Override
    public List<SkArticleCategoryCn> getNodeTree(Integer channelId, String locale) {
        switch (locale) {
            case "zh":
                List<SkArticleCategoryCn> cnList = skArticleCategoryCnMapper.selectAll(channelId);
                List<SkArticleCategoryCn> pList = cnList.stream().filter(e -> e.getParentId() == 0).collect(Collectors.toList());
                return sortCategory(pList, cnList);
            case "en":
                List<SkArticleCategoryCn> enList= skArticleCategoryEnMapper.selectAll(channelId);
                List<SkArticleCategoryCn> pEnList = enList.stream().filter(e -> e.getParentId() == 0).collect(Collectors.toList());
                return sortCategory(pEnList, enList);
            default:
                List<SkArticleCategoryCn> cnList1 = skArticleCategoryCnMapper.selectAll(channelId);
                List<SkArticleCategoryCn> pCnList1 = cnList1.stream().filter(e -> e.getParentId() == 0).collect(Collectors.toList());
                return sortCategory(pCnList1, cnList1);
        }
    }

    /**
     * @param
     * @Description 递归 给Category排成无限极树
     * @author yuwentao
     */
    public List<SkArticleCategoryCn> sortCategory(List<SkArticleCategoryCn> pList, List<SkArticleCategoryCn> totalList) {
        //把儿子插入父亲
        pList.stream().forEach(e -> e.setChildren(totalList.stream().filter(p -> p.getParentId().equals(e.getId())).collect(Collectors.toList())));
        //如果父亲的儿子不为空，那继续递归
        pList.stream().forEach(p -> sortCategory(p.getChildren().stream().filter(e->e.getId()!=null).collect(Collectors.toList()), totalList));
        return pList;
    }

    // 根据传入的id(String类型) 转化成 list类型 用以调用dao层方法
    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }

    // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在set中 --zh
    public void getAllChildrenCategoryCn(String id,Set categorySet) {
        categorySet.add(id);
        List<String> baseCategory = skArticleCategoryCnMapper.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categorySet.add(x);
            getAllChildrenCategoryCn(x,categorySet);
        });
    }

    // 根据传来的ID，获取所有栏目ID(传来的ID和及其子孙ID)存储在set中 --en
    public void getAllChildrenCategoryEn(String id,Set categorySet) {
        categorySet.add(id);
        List<String> baseCategory = skArticleCategoryEnMapper.getBaseCategory(id);
        baseCategory.forEach(x -> {
            categorySet.add(x);
            getAllChildrenCategoryEn(x,categorySet);
        });
    }

    // 根据传来的ID，删除此ID和此ID下的子孙ID栏目类别 ,级联删除与栏目ID集相关的内容
    @Override
    public int deleteAllCategoryCn(String id,String locale) {
        Set<String> categorySet = new HashSet<>();
        List<String> idList = getList(id);
        switch (locale) {
            case "zh":
                idList.forEach(x -> {
                    getAllChildrenCategoryCn(x,categorySet);
                });
                // Set --> List
                List list1 = new ArrayList(categorySet);
                return skArticleCategoryCnMapper.deleteAboutArticleCategoryCn(list1);
            case "en":
                idList.forEach(x -> {
                    getAllChildrenCategoryEn(x,categorySet);
                });
                // Set --> List
                List list2 = new ArrayList(categorySet);
                return skArticleCategoryEnMapper.deleteAboutArticleCategoryCn(list2);
            default:
                idList.forEach(x -> {
                    getAllChildrenCategoryCn(x,categorySet);
                });
                // Set --> List
                List list3 = new ArrayList(categorySet);
                return skArticleCategoryCnMapper.deleteAboutArticleCategoryCn(list3);
        }
    }

    @Override
    public int updateMoreSortId(List<SkArticleCategoryCn> skArticleCategoryCnList,String locale) {
        switch (locale) {
            case "zh":
                return skArticleCategoryCnMapper.updateMoreSortId(skArticleCategoryCnList);
            case "en":
                return skArticleCategoryEnMapper.updateMoreSortId(skArticleCategoryCnList);
            default:
                return skArticleCategoryCnMapper.updateMoreSortId(skArticleCategoryCnList);
        }
    }

    @Override
    public List<SkArticleCategoryCn> getNextInfos(Integer parentId) {
        return skArticleCategoryCnMapper.getNextInfos(parentId);
    }

    @Override
    public List<SkArticleCategoryCn> selectNewsCategory(String locale) {
        switch (locale) {
            case "zh":
                return skArticleCategoryCnMapper.selectNewsCategory();
            case "en":
                return skArticleCategoryEnMapper.selectNewsCategory();
            default:
                return skArticleCategoryCnMapper.selectNewsCategory();
        }
    }

    @Override
    public List<SkArticleCategoryCn> selectAllCategory() {
        return skArticleCategoryCnMapper.selectAllCategory();
    }

}


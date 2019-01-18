package com.niit.service.cms.commonMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.01 20:05
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.01 20:05
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
public class CommonMethod {

    /**
     * @param originalCategoryIds 需要查找最父类的子类栏目id列表
     * @param categoryIdMap       子类栏目与父类栏目列表对应表
     * @param categoryList        所有栏目列表
     * @Description 递归拿到指定categoryId列表的的各个父类id列表
     * @author yuwentao
     */
    public Map<Integer, List<SkArticleCategoryCn>> getParentList(List<Integer> originalCategoryIds, Map<Integer, List<SkArticleCategoryCn>> categoryIdMap, List<SkArticleCategoryCn> categoryList) {
        originalCategoryIds.stream().forEach(p ->
                categoryList.stream().filter(e -> e.getId().equals(categoryIdMap.get(p).get(0).getParentId())).forEach(e -> {
                    if (e.getParentId() == 0) {
                        List<SkArticleCategoryCn> temp = categoryIdMap.get(p);
                        temp.add(0, e);
                        categoryIdMap.put(p, temp);
                    } else {
                        List<SkArticleCategoryCn> temp = categoryIdMap.get(p);
                        temp.add(0, e);
                        categoryIdMap.put(p, temp);
                        getParentList(originalCategoryIds, categoryIdMap, categoryList);
                    }
                })
        );
        return categoryIdMap;
    }

    /**
     * @param originalCategoryIds 需要查找最父类的子类栏目id列表
     * @param categoryIdMap       子类栏目与最父类栏目对应表
     * @param categoryList        所有栏目列表
     * @Description 递归拿到指定categoryId列表的的各个最父类id
     * @author yuwentao
     */
    public Map<Integer, SkArticleCategoryCn> getParent(List<Integer> originalCategoryIds, Map<Integer, SkArticleCategoryCn> categoryIdMap, List<SkArticleCategoryCn> categoryList) {
        originalCategoryIds.stream().forEach(p ->
                categoryList.stream().filter(e -> e.getId().equals(categoryIdMap.get(p).getParentId())).forEach(e -> {
                    if (e.getParentId() == 0) {
                        categoryIdMap.put(p, e);
                    } else {
                        categoryIdMap.put(p, e);
                        getParent(originalCategoryIds, categoryIdMap, categoryList);
                    }
                })
        );
        return categoryIdMap;
    }

    //递归拿到指定categoryId的儿子栏目的id集合
    public List<Integer> getAllChildren(List<SkArticleCategoryCn> totalList, List<SkArticleCategoryCn> pList, List<Integer> categoryIdList) {
        pList.stream().forEach(e -> {
            categoryIdList.add(e.getId());
            getAllChildren(totalList, totalList.stream().filter(p -> p.getParentId().equals(e.getId())).collect(Collectors.toList()), categoryIdList);
        });
        return categoryIdList;
    }

    public PageInfo<Object> likeSelectAll(int currentPage, int pageSize, String title, Integer categoryId, String key, Integer channelId, Object articleMapper, Object categoryMapper, String orderBy) {
        PageInfo<Object> listInfo = null;
        List<Integer> categoryIdList = new LinkedList<>();
        try {
            Method categoryMethod = categoryMapper.getClass().getMethod("selectAll", Integer.class);
            List<SkArticleCategoryCn> totalList = (List<SkArticleCategoryCn>) categoryMethod.invoke(categoryMapper, channelId);
            List<SkArticleCategoryCn> pList;
            if (categoryId == 0) {
                //categoryId=0,则去找parentID()=0的数据
                pList = totalList.stream().filter(e -> e.getParentId().equals(0)).collect(Collectors.toList());
            } else {
                pList = totalList.stream().filter(e -> e.getId().equals(categoryId)).collect(Collectors.toList());
            }
            //递归拿到指定categoryId的儿子栏目的id集合
            categoryIdList = getAllChildren(totalList, pList, categoryIdList);
            //数据库查询
            if (categoryIdList != null && categoryIdList.size() > 0) {
                PageHelper.startPage(currentPage, pageSize);
                Method articleMethod = articleMapper.getClass().getMethod("likeSelectAllByCategoryId", String.class, String.class, List.class, String.class);
                List<Object> list = (List<Object>) articleMethod.invoke(articleMapper, title, key, categoryIdList, orderBy);
                listInfo = new PageInfo<>(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
}

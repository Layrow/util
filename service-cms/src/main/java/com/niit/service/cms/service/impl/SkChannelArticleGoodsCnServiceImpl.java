package com.niit.service.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.common.utils.Tools;
import com.niit.service.cms.commonMethod.CommonMethod;
import com.niit.service.cms.dao.SkArticleCategoryCnMapper;
import com.niit.service.cms.dao.SkArticleCategoryEnMapper;
import com.niit.service.cms.dao.SkChannelArticleGoodsCnMapper;
import com.niit.service.cms.dao.SkChannelArticleGoodsEnMapper;
import com.niit.service.cms.pojo.SkArticleCategoryCn;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCn;
import com.niit.service.cms.service.SkChannelArticleGoodsCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkChannelArticleGoodsCnServiceImpl implements SkChannelArticleGoodsCnService {
    @Autowired
    private SkChannelArticleGoodsCnMapper skChannelArticleGoodsCnMapper;
    @Autowired
    private SkChannelArticleGoodsEnMapper skChannelArticleGoodsEnMapper;
    @Autowired
    private SkArticleCategoryCnMapper cc;
    @Autowired
    private SkArticleCategoryEnMapper ce;
    @Autowired
    private CommonMethod commonMethod;

    public Object getCategoryMapper(String local) {
        return Tools.getMapper(local, cc, ce);
    }

    public Object getGoodsMapper(String local) {
        return Tools.getMapper(local, skChannelArticleGoodsCnMapper, skChannelArticleGoodsEnMapper);
    }


    @Override
    public Map<String, Object> easyLikeSelectAll(String locale, String title, Integer channelId) {
        CommonMethod commonMethod = new CommonMethod();

        List<Integer> originalCategoryIds = new LinkedList<>();
        Map<Integer, SkArticleCategoryCn> categoryIdMap = new LinkedHashMap<>();
        Map<String, Object> totalResultMap = new LinkedHashMap<>();
        Map<Integer, List<SkChannelArticleGoodsCn>> resultMap = new LinkedHashMap<>();
        List<SkArticleCategoryCn> mainCategoryList = new LinkedList<>();
        try {
            Object goodsMapper = getGoodsMapper(locale);
            Object categoryMapper = getCategoryMapper(locale);
            //查询所有的goods信息列表
            Method articleMethod = goodsMapper.getClass().getMethod("likeSelectAllByCategoryId", String.class, String.class, List.class, String.class);
            List<SkChannelArticleGoodsCn> goodsList = (List<SkChannelArticleGoodsCn>) articleMethod.invoke(goodsMapper, title, "audited", null, "");


            //把goods中的栏目id放入originalCategoryIds列表
            goodsList.stream().forEach(e -> originalCategoryIds.add(e.getCategoryId()));
            //查询所有的栏目信息列表
            Method categoryMethod = categoryMapper.getClass().getMethod("selectAll", Integer.class);
            List<SkArticleCategoryCn> categoryList = (List<SkArticleCategoryCn>) categoryMethod.invoke(categoryMapper, channelId);
            //把所有的originalCategoryIds当作key放入categoryIdMap,并查出对应的栏目信息实体当作value放入categoryIdMap
            originalCategoryIds.stream().distinct().forEach(e -> categoryIdMap.put(e, categoryList.stream().filter(c -> c.getId().equals(e)).findFirst().get()));
            //调用getParent方法递归拿到指定栏目id对应的最父类栏目信息
            Map<Integer, SkArticleCategoryCn> result = commonMethod.getParent(originalCategoryIds, categoryIdMap, categoryList);

            originalCategoryIds.stream().distinct().forEach(e -> {
                List<SkChannelArticleGoodsCn> newList = new LinkedList<>();
                goodsList.stream().filter(p -> p.getCategoryId().equals(e)).forEach(p -> {

//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        Date date = sdf.parse(sdf.format(new Date()));
//                        System.out.println(date);
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }
//
//                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                    SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
//                    String d1=sf.format(p.getAddTime());
//                    String d2=sf.format(p.getUpdateTime());
//                    try {
//                        Date nd1=sf.parse(d1);
//                        Date nd2=sf.parse(d2);
//
//                        p.setAddTime(nd1);
//                        p.setAddTime(nd2);
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }

                    newList.add(p);
                });
                if (resultMap.containsKey(result.get(e).getId())) {
                    List<SkChannelArticleGoodsCn> temp = resultMap.get(result.get(e).getId());
                    temp.addAll(newList);
                    if (temp.size() > 12) {
                        resultMap.put(result.get(e).getId(), temp.subList(0, 12));
                    } else {
                        resultMap.put(result.get(e).getId(), temp);
                    }
                } else {
                    if (newList.size() > 12) {
                        resultMap.put(result.get(e).getId(), newList.subList(0, 12));
                    } else {
                        resultMap.put(result.get(e).getId(), newList);
                    }
                }
                mainCategoryList.add(result.get(e));
                //去重复
                HashSet h = new HashSet(mainCategoryList);
                mainCategoryList.clear();
                mainCategoryList.addAll(h);
            });
            totalResultMap.put("resultMap", resultMap);
            totalResultMap.put("mainCategoryList", mainCategoryList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalResultMap;
    }

    @Override
    public Map<String, Object> getArticleCountByCategory(Integer channelId, String locale, String keyword) {
        Map<String, Object> totalMap = new LinkedHashMap<>();
        Map<Integer, Integer> countMap = new LinkedHashMap<>();
        Object categoryMapper = getCategoryMapper(locale);
        Object goodsMapper = getGoodsMapper(locale);
        List<SkArticleCategoryCn> categoryMainList;
        List<SkArticleCategoryCn> categoryAllList;
        Map<Integer, List<Integer>> mainCategoryChildrenListMap = new LinkedHashMap<>();
        try {
            Method selectMainMethod = categoryMapper.getClass().getMethod("getMainCategory", Integer.class);
            categoryMainList = (List<SkArticleCategoryCn>) selectMainMethod.invoke(categoryMapper, channelId);
            Method selectAllMethod = categoryMapper.getClass().getMethod("selectAll", Integer.class);
            categoryAllList = (List<SkArticleCategoryCn>) selectAllMethod.invoke(categoryMapper, channelId);

            categoryMainList.stream().forEach(e -> {
                List<Integer> categoryIdList = new LinkedList<>();
                List<SkArticleCategoryCn> pList = new LinkedList<>();
                pList.add(e);
                mainCategoryChildrenListMap.put(e.getId(), commonMethod.getAllChildren(categoryAllList, pList, categoryIdList));
            });

            Method selectCountMethod = goodsMapper.getClass().getMethod("selectEachCountByMainCategoryId", String.class, String.class, Map.class);
            List<Integer> countList = (List<Integer>) selectCountMethod.invoke(goodsMapper, keyword, "audited", mainCategoryChildrenListMap);

            for (int i = 0; i < categoryMainList.size(); i++) {
                countMap.put(categoryMainList.get(i).getId(), countList.get(i));
            }

            totalMap.put("categoryList", categoryMainList);
            totalMap.put("countMap", countMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalMap;
    }

    public Object getMapper(String locale) {
        Map<String, Object> foo = new LinkedHashMap<>();
        foo.put("zh", skChannelArticleGoodsCnMapper);
        foo.put("en", skChannelArticleGoodsEnMapper);
        Object mapper = foo.get(locale);
        mapper = (mapper != null) ? mapper : skChannelArticleGoodsCnMapper;
        return mapper;
    }

//    public Object getMapper2(String locale) {
//        Map<String, Object> foo = new LinkedHashMap<>();
//        foo.put("zh", cc);
//        foo.put("en", ce);
//        Object mapper = foo.get(locale);
//        mapper = (mapper != null) ? mapper : cc;
//        return mapper;
//    }


    @Override
    public int deleteByPrimaryKey(Integer id, String locale) {
        return skChannelArticleGoodsCnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SkChannelArticleGoodsCn record, String locale) {
        return skChannelArticleGoodsCnMapper.insert(record);
    }

    @Override
    public int insertSelective(SkChannelArticleGoodsCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleGoodsCnMapper.insertSelective(record);
            case "en":
                return skChannelArticleGoodsEnMapper.insertSelective(record);
            default:
                return skChannelArticleGoodsCnMapper.insertSelective(record);
        }
    }

    @Override
    public int batchUp(List<SkChannelArticleGoodsCn> lis, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleGoodsCnMapper.batchUp(lis);
            case "en":
                return skChannelArticleGoodsEnMapper.batchUp(lis);
            default:
                return skChannelArticleGoodsCnMapper.batchUp(lis);
        }
    }

    @Override
    public void updateTo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleGoodsCnMapper.updateTo(list);
            case "en":
                skChannelArticleGoodsEnMapper.updateTo(list);
            default:
                skChannelArticleGoodsCnMapper.updateTo(list);
        }

    }

    @Override
    public void updateRe(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleGoodsCnMapper.updateRe(list);
            case "en":
                skChannelArticleGoodsEnMapper.updateRe(list);
            default:
                skChannelArticleGoodsCnMapper.updateRe(list);
        }

    }

    @Override
    public void updateHo(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleGoodsCnMapper.updateHo(list);
            case "en":
                skChannelArticleGoodsEnMapper.updateHo(list);
            default:
                skChannelArticleGoodsCnMapper.updateHo(list);
        }

    }

    @Override
    public SkChannelArticleGoodsCn selectByPrimaryKey(Integer id, String locale) {
        return skChannelArticleGoodsCnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SkChannelArticleGoodsCn record, String locale) {
        switch (locale) {
            case "zh":
                return skChannelArticleGoodsCnMapper.updateByPrimaryKeySelective(record);
            case "en":
                return skChannelArticleGoodsEnMapper.updateByPrimaryKeySelective(record);
            default:
                return skChannelArticleGoodsCnMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SkChannelArticleGoodsCn record, String locale) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SkChannelArticleGoodsCn record, String locale) {
        return skChannelArticleGoodsCnMapper.updateByPrimaryKey(record);
    }

    //批量删除
    @Override
    public void deleteAd(String id, String locale) {
        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleGoodsCnMapper.deleteAd(list);
            case "en":
                skChannelArticleGoodsEnMapper.deleteAd(list);
            default:
                skChannelArticleGoodsCnMapper.deleteAd(list);
        }

    }

    //批量审核
    @Override
    public void updateSt(String id, String locale) {

        List<String> list = getList(id);
        switch (locale) {
            case "zh":
                skChannelArticleGoodsCnMapper.updateSt(list);
            case "en":
                skChannelArticleGoodsEnMapper.updateSt(list);
            default:
                skChannelArticleGoodsCnMapper.updateSt(list);
        }

    }

    //分页查找
    @Override
    public PageInfo<SkChannelArticleGoodsCn> selectAll(int currentPage, int pageSize, String locale) {
        List<SkChannelArticleGoodsCn> list;
        PageInfo<SkChannelArticleGoodsCn> listInfo;
        switch (locale) {
            case "zh":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleGoodsCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            case "en":
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleGoodsEnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
            default:
                PageHelper.startPage(currentPage, pageSize);
                //执行SQL语句（list->分页后的数据）
                list = skChannelArticleGoodsCnMapper.selectAll();
                //把取到的list封装进PageInfo(PageInfo->分页信息+分页后的数据）
                listInfo = new PageInfo<>(list);
                return listInfo;
        }
    }

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


    /**
     * @param
     * @Description getMapper函数拿到对应语言的mapper, 利用反射调用方法，若要增加语言，无需改动service层
     * @author yuwentao
     */
    //分页模糊查询
    @Override
    public PageInfo<Object> likeSelectAll(int currentPage, int pageSize, String title, String locale, Integer categoryId, String key, Integer channelId, String orderBy) {
        CommonMethod commonMethod = new CommonMethod();
        PageInfo<Object> pageInfo = commonMethod.likeSelectAll(currentPage, pageSize, title, categoryId, key, channelId, getGoodsMapper(locale), getCategoryMapper(locale), orderBy);
        return pageInfo;
    }

    //递归拿到指定categoryId的儿子栏目的id集合
    public List<Integer> test(List<SkArticleCategoryCn> totalList, List<SkArticleCategoryCn> pList, List<Integer> categoryIdList) {
        pList.stream().forEach(e -> {
            categoryIdList.add(e.getId());
            test(totalList, totalList.stream().filter(p -> p.getParentId().equals(e.getId())).collect(Collectors.toList()), categoryIdList);
        });
        return categoryIdList;
    }


    /**
     * @param
     * @Description getMapper函数拿到对应语言的mapper, 利用反射调用方法，若要增加语言，无需改动service层
     * @author yuwentao
     */
    //分页依据categoryId、key查询
    @Override
    public PageInfo<SkChannelArticleGoodsCn> FuzzySearchBy(Integer categoryId, String key, int currentPage, int pageSize, String locale) {
        PageInfo<SkChannelArticleGoodsCn> listInfo;
        Object mapper = getMapper(locale);
        try {
            PageHelper.startPage(currentPage, pageSize);
            Method method = mapper.getClass().getMethod("selectAllByCategoryId", Integer.class, String.class);
            List<SkChannelArticleGoodsCn> list = (List<SkChannelArticleGoodsCn>) method.invoke(mapper, categoryId, key);
            listInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            return listInfo = null;
        }
        return listInfo;
    }

}

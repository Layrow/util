package com.niit.service.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.common.utils.Tools;
import com.niit.service.cms.dao.SkAdContentMapper;
import com.niit.service.cms.dao.SkAdMapper;
import com.niit.service.cms.pojo.SkAd;
import com.niit.service.cms.pojo.SkAdContent;
import com.niit.service.cms.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName SkAdSerivceImpl
 * @Description
 * @Author liyuhao
 * @Date 2018/12/11 10:02
 **/
@Service
public class SkAdSerivceImpl implements SkAdService {

    @Autowired
    private SkAdMapper skAdMapper;
    @Autowired
    private SkAdContentMapper skAdContentMapper;

    // 查询所有广告位,默认查询所有的广告位，可以添加参数用来查询可用的广告位(status = 1)
    @Override
    public PageInfo<SkAd> selectAllAdsense(Integer status, Integer currentPage, Integer pageSize) {
        PageInfo<SkAd> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkAd> skAdList = skAdMapper.selectAllAdsense(status);
        pageInfo = new PageInfo<>(skAdList);
        return pageInfo;
    }

    // 查询所有广告位
    @Override
    public List<SkAd> selectAllAdsenses() {
        return skAdMapper.selectAllAdsenses();
    }

    // 批量删除广告位
    @Override
    public int deleteMoreAd(String id) {
        List<String> list = Tools.getList(id);
        return skAdMapper.deleteMoreAd(list);
    }

    // 修改广告位
    @Override
    public int updateAd(SkAd skAd) {
        return skAdMapper.updateByPrimaryKeySelective(skAd);
    }

    // 删除广告
    @Override
    public int deleteAdContentByPrimaryKey(Integer id) {
        return skAdContentMapper.deleteByPrimaryKey(id);
    }

    // 添加广告
    @Override
    public int insertAdContentSelective(SkAdContent record) {
        return skAdContentMapper.insertSelective(record);
    }

    // 修改广告内容
    @Override
    public int updateByPrimaryKey(SkAdContent record) {
        return skAdContentMapper.updateByPrimaryKey(record);
    }

    // 删除某个广告位下的所有广告
    @Override
    public int deleteByAdId(Integer adId) {
        return skAdContentMapper.deleteByAdId(adId);
    }

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    @Override
    public PageInfo<SkAdContent> selectByAdId(Integer adId, Integer status, Integer currentPage, Integer pageSize) {
        PageInfo<SkAdContent> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkAdContent> skAdContentsList = skAdContentMapper.selectByAdId(adId, status);
        pageInfo = new PageInfo<>(skAdContentsList);
        return pageInfo;
    }

    // 根据keycode查询
    @Override
    public PageInfo<SkAdContent> selectByKeycode(String keycode, Integer status, Integer currentPage, Integer pageSize) {
        PageInfo<SkAdContent> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<SkAdContent> skAdContentsList = skAdContentMapper.selectByKeycode(keycode, status);
        pageInfo = new PageInfo<>(skAdContentsList);
        return pageInfo;
    }

    // 新增广告位
    @Override
    public int insert(SkAd record) {
        return skAdMapper.insert(record);
    }

    // 批量审核广告
    @Override
    public int updateMoreAdContent(String id) {
        List<String> list = Tools.getList(id);
        return skAdContentMapper.updateMoreAdContent(list);
    }

    // 批量删除广告
    @Override
    public int deleteMoreAdContent(String id) {
        List<String> list = Tools.getList(id);
        return skAdContentMapper.deleteMoreAdContent(list);
    }

    // 根据Title模糊查询
    @Override
    public PageInfo<SkAd> likeSelectAdAllByTitle(String title, Integer currentPage, Integer pageSize) {
        PageInfo<SkAd> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        final List<SkAd> adList = skAdMapper.likeSelectAdAllByTitle(title);
        pageInfo = new PageInfo<>(adList);
        return pageInfo;
    }

    // 批量修改广告排序值
    @Override
    public int updateAdContentMoreSortId(List<SkAdContent> skAdContentList) {
        return skAdContentMapper.updateAdContentMoreSortId(skAdContentList);
    }

    // 查询所有广告
    @Override
    public String selectAllAd(Integer status, String title, Integer currentPage, Integer pageSize) {
        PageInfo<LinkedHashMap<String, Object>> pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        List<LinkedHashMap<String, Object>> linkedHashMaps = skAdContentMapper.selectAllAd(status, title);
        pageInfo = new PageInfo<>(linkedHashMaps);
        ArrayList<SkAdContent> listAdContent = new ArrayList<>();
        ArrayList<String> listTitleList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        pageInfo.getList().forEach(x -> {
            // 取得广告属性值
            Integer adid = (Integer) x.get("adid");
            Integer bid = (Integer) x.get("bid");
            String adtitle = (String) x.get("adtitle");
            String adurl = (String) x.get("adurl");
            String adhref = (String) x.get("adhref");
            Integer adstatus = (Integer) x.get("adstatus");
            Integer adorder = (Integer) x.get("adorder");
            // 封装广告对象
            SkAdContent skAdContent = new SkAdContent();
            skAdContent.setId(bid);
            skAdContent.setAdId(adid);
            skAdContent.setAdUrl(adurl);
            skAdContent.setAdHref(adhref);
            skAdContent.setAdStatus(adstatus);
            skAdContent.setAdOrder(adorder);

            // 封装完成之后，清空list
            x.clear();
            x.put("skAdContent", skAdContent);
            listAdContent.add(skAdContent);
            listTitleList.add(adtitle);
        });
        map.put("pageInfo", pageInfo);
        map.put("listAdContentTitle", listTitleList);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }


}

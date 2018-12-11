package com.niit.service.cms.service.impl;

import com.niit.common.utils.Tools;
import com.niit.service.cms.dao.SkAdContentMapper;
import com.niit.service.cms.dao.SkAdMapper;
import com.niit.service.cms.pojo.SkAd;
import com.niit.service.cms.pojo.SkAdContent;
import com.niit.service.cms.service.SkAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SkAd> selectAllAdsense(Integer status) {
        return skAdMapper.selectAllAdsense(status);
    }

    // 批量删除广告位
    @Override
    public int deleteMoreAd(String id) {
        List<String> list = Tools.getList(id);
        return skAdMapper.deleteMoreAd(list);
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

    // 审核广告
    @Override
    public int updateByPrimaryKeySelective(SkAdContent record) {
        return skAdContentMapper.updateByPrimaryKeySelective(record);
    }

    // 删除某个广告位下的所有广告
    @Override
    public int deleteByAdId(Integer adId) {
        return skAdContentMapper.deleteByAdId(adId);
    }

    // 查询特定广告位下的所有广告 order排序 status为1 或 status为0
    @Override
    public List<SkAdContent> selectByAdId(Integer adId, Integer status) {
        return skAdContentMapper.selectByAdId(adId,status);
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

}

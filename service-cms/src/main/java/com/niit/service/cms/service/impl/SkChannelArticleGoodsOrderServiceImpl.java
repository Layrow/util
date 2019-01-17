package com.niit.service.cms.service.impl;

import com.niit.service.cms.dao.SkChannelArticleGoodsCostumesMapper;
import com.niit.service.cms.dao.SkChannelArticleGoodsOrderMapper;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;
import com.niit.service.cms.pojo.SkChannelArticleGoodsOrder;
import com.niit.service.cms.service.SkChannelArticleGoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.28 16:49
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.28 16:49
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class SkChannelArticleGoodsOrderServiceImpl implements SkChannelArticleGoodsOrderService {

    @Autowired
    SkChannelArticleGoodsOrderMapper skChannelArticleGoodsOrderMapper;
    @Autowired
    SkChannelArticleGoodsCostumesMapper skChannelArticleGoodsCostumesMapper;

    @Override
    public Integer checkIsOwned(Integer userId, Integer goodId) {
        List<SkChannelArticleGoodsOrder> list =skChannelArticleGoodsOrderMapper.selectByUserId(userId);
        return list.stream().filter(e->e.getGoodsId()==goodId).collect(Collectors.toList()).size();
    }

    @Override
    public List<SkChannelArticleGoodsCostumes> selectByUserId(Integer userId) {
        List<SkChannelArticleGoodsOrder> goodsList=
        skChannelArticleGoodsOrderMapper.selectByUserId(userId);
        //拿到订单表里的所有goodId列表,然后goodId列表去造型表批量查询对应的造型
        List<Integer> goodsIdList= goodsList.stream().map(e->e.getGoodsId()).collect(Collectors.toList());
        return skChannelArticleGoodsCostumesMapper.selectByGoodsId(goodsIdList);

    }

    @Override
    public Integer generateOrders(SkChannelArticleGoodsOrder record) {
        return skChannelArticleGoodsOrderMapper.insertSelective(record);
    }
}

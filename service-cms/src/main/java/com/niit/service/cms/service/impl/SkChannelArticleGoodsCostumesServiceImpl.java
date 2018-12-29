package com.niit.service.cms.service.impl;

import com.niit.service.cms.dao.SkChannelArticleGoodsCostumesMapper;
import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;
import com.niit.service.cms.service.SkChannelArticleGoodsCostumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.28 15:26
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.28 15:26
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class SkChannelArticleGoodsCostumesServiceImpl implements SkChannelArticleGoodsCostumesService {

    @Autowired
    SkChannelArticleGoodsCostumesMapper skChannelArticleGoodsCostumesMapper;


    @Override
    public List<SkChannelArticleGoodsCostumes> selectByGoodId(Integer goodId) {
        return skChannelArticleGoodsCostumesMapper.selectByGoodId(goodId);
    }

    @Override
    public List<SkChannelArticleGoodsCostumes> selectByGoodsId(List<Integer> goodsId) {
        return skChannelArticleGoodsCostumesMapper.selectByGoodsId(goodsId);
    }
}

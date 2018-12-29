package com.niit.service.cms.service;

import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;

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
public interface SkChannelArticleGoodsCostumesService {

    List<SkChannelArticleGoodsCostumes> selectByGoodId(Integer goodId);

    List<SkChannelArticleGoodsCostumes> selectByGoodsId(List<Integer> goodsId);
}

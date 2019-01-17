package com.niit.service.cms.service;

import com.niit.service.cms.pojo.SkChannelArticleGoodsCostumes;
import com.niit.service.cms.pojo.SkChannelArticleGoodsOrder;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.12.28 16:48
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.12.28 16:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface SkChannelArticleGoodsOrderService {

    Integer checkIsOwned(Integer userId,Integer goodId);

    List<SkChannelArticleGoodsCostumes> selectSpritesByUserId(Integer userId);

    List<SkChannelArticleGoodsCostumes> selectBackdropsByUserId(Integer userId);

    Integer generateOrders(SkChannelArticleGoodsOrder record);

}

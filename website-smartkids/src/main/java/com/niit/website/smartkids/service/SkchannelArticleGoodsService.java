package com.niit.website.smartkids.service;

import com.github.pagehelper.PageInfo;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsCn;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsOrder;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.23 09:54
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.23 09:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface SkchannelArticleGoodsService {

    String generateOrders(SkChannelArticleGoodsOrder record);
    String checkIsOwned(Integer goodId,Integer userId);
    String easyLikeSelectAll(String keyword, Integer channelId, String locale);

    //通过父类 拿到父类栏目的内容（商品、新闻）总数
    String getArticleCountByCategory(Integer channelId, String locale,String keyword);

    PageInfo<SkChannelArticleGoodsCn> getByCategory(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale, Integer channelId,String orderBy);
}

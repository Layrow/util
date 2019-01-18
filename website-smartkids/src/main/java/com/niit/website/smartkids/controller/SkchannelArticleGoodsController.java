package com.niit.website.smartkids.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.niit.website.smartkids.enums.IntegralActionsEnum;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsCn;
import com.niit.website.smartkids.pojo.SkChannelArticleGoodsOrder;
import com.niit.website.smartkids.pojo.member.SkMemberIntegral;
import com.niit.website.smartkids.service.SkchannelArticleGoodsService;
import com.niit.website.smartkids.service.memberservice.IMemberItegralService;
import com.niit.website.smartkids.service.memberservice.impl.MemberServiceIntegralmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.23 09:52
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.23 09:52
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/smartkids/mall")
public class SkchannelArticleGoodsController {
    @Autowired
    SkchannelArticleGoodsService skchannelArticleGoodsService;
    @Autowired
    IMemberItegralService iMemberItegralService;
    @Autowired
    MemberServiceIntegralmpl memberServiceIntegralmpl;

    @GetMapping(value = "/purchased_goodids")
    public String purchasedGoodIds(Integer userId) {
            String json = skchannelArticleGoodsService.selectGoodIdsByUserId(userId);
            return json;
    }


    @PostMapping
    public String purchaseGoods(@RequestBody SkChannelArticleGoodsOrder record) {
        Integer userID = record.getPurchaserId();
        Integer goodId = record.getGoodsId();

        String p = iMemberItegralService.getTatal(userID);
        Integer point = Integer.parseInt(p);

        String isOwned;
        try {
            isOwned = skchannelArticleGoodsService.checkIsOwned(goodId, userID);
            int i = Integer.parseInt(isOwned);

            if (i >= 1) {
                return "已采集过此素材，请前往素材库查看！";
            } else if (point < record.getPrice()) {
                return "积分不足！";
            } else {
                record.setBuyingTime(new Date());
                SkMemberIntegral skMemberIntegral = new SkMemberIntegral();
                skMemberIntegral.setUserId(record.getPurchaserId());
                skMemberIntegral.setActions(IntegralActionsEnum.POST_BUY.getAction());
                skMemberIntegral.setOperation(IntegralActionsEnum.POST_BUY.getOperation());
                skMemberIntegral.setNumbers(record.getPrice().intValue());
                memberServiceIntegralmpl.interAction(skMemberIntegral);
                skchannelArticleGoodsService.generateOrders(record);
                return "采集成功！";
            } //用户积分价格 且 用户库存中无此商品则生成订单
        } catch (Exception e) {
            e.printStackTrace();
            return "服务器异常！";
        }

    }

    @GetMapping(value = "/main_category_fuzz_search")
    public Map<String, Object> fuzzSearchAndMainCategory(String keyword, Integer channelId, String locale) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            String mapString = skchannelArticleGoodsService.easyLikeSelectAll(keyword, channelId, locale);
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (mapString == null || mapString.isEmpty()) {
                return result;
            }
            result = gson.fromJson(mapString, type);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * @param channelId, locale
     * @Description TODO
     * @author yuwentao
     */
    @GetMapping(value = "/main_category_article_count")
    public Map<String, Object> getMainCategoryAndArticleCount(Integer channelId, String locale, @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord) {
        Map<String, Object> map = null;

        try {
            String mapString = skchannelArticleGoodsService.getArticleCountByCategory(channelId, locale, keyWord);
            //把String转成Map
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Gson gson = new Gson();
            if (mapString == null || mapString.isEmpty()) {
                return map;
            }
            map = gson.fromJson(mapString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @PostMapping(value = "/category")
    public PageInfo<SkChannelArticleGoodsCn> getByCategory(Integer categoryId, String key, int currentPage, int pageSize, String title, String locale, Integer channelId, @RequestParam(value = "orderBy", required = false, defaultValue = "click") String orderBy) {
        PageInfo<SkChannelArticleGoodsCn> pageInfoList = null;
        try {
            pageInfoList = skchannelArticleGoodsService.getByCategory(categoryId, key, currentPage, pageSize, title, locale, channelId, orderBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfoList;
    }


}

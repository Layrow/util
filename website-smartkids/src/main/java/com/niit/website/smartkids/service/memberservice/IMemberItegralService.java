package com.niit.website.smartkids.service.memberservice;

import com.niit.website.smartkids.pojo.member.SkMemberIntegral;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/19
 * @since 1.0.0
 */
public interface IMemberItegralService {
    /**
     * 取得指定用户的所有积分
     * @param uid
     * @return
     */
    String getTatal(Integer uid);

    /**
     * 增加操作记录
     * @param record
     * @return
     */
    String  interAction(SkMemberIntegral record);

}

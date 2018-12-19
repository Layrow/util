package com.niit.service.member.service;

import com.niit.service.member.pojo.SkMemberIntegral;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/18
 * @since 1.0.0
 */
public interface ISkMemberIntegralService {
    /**
     * 取得用户的所有积分
     * @param id
     * @return
     */
    int getMemberIntegral(Integer id);

    boolean updateIntegral(SkMemberIntegral record);
}

package com.niit.service.member.service.impl;

import com.niit.service.member.dao.SkMemberIntegralMapper;
import com.niit.service.member.pojo.SkMemberIntegral;
import com.niit.service.member.service.ISkMemberIntegralService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 冯永辉
 * @create 2018/12/18
 * @since 1.0.0
 */
@Service
public class SkMemberIntegralServiceImpl implements ISkMemberIntegralService {

    @Resource
    SkMemberIntegralMapper skMemberIntegralMapper;
    /**
     * 取得用户的所有积分
     *
     * @param id
     * @return
     */
    @Override
    public int getMemberIntegral(Integer id) {
        //增加的分值
        Integer add = skMemberIntegralMapper.selectAddIntegral(id);
        //减少的分值
        Integer del = skMemberIntegralMapper.selectDelIntegral(id);
        //如果没有加分,则置为0
        if (add==null){
            add=0;
        }
        //如果没有减分,则置为0
        if (del==null){
            del=0;
        }
        //总得分 奖励分-惩罚分
        return (add-del);
    }

    @Override
    public boolean updateIntegral(SkMemberIntegral record) {
        return false;
    }
}

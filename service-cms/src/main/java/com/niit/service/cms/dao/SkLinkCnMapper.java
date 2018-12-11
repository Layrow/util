package com.niit.service.cms.dao;

import com.niit.service.cms.pojo.SkChannelArticleNewsCn;
import com.niit.service.cms.pojo.SkLinkCn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SkLinkCnMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * @Description 批量更新
     * @author yuwentao
     * @param
     */
    Integer updateByList(List<SkLinkCn> record);

    /**
     * @param null
     * @Description 实现选取所有数据
     * @author yuwentao
     */
    List<SkLinkCn> selectAll();

    int insert(SkLinkCn record);

    int insertSelective(SkLinkCn record);

    SkLinkCn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkLinkCn record);

    /**
     * @param record
     * @Description 更新单条数据
     * @author yuwentao
     */
    int updateByPrimaryKey(SkLinkCn record);

    /**
     * 实现链接数据的批量删除操作,执行到此时,list集合内一定有数据
     * @param ids
     * @return
     */
    boolean deleteBatchByPrimaryKey(List<String> ids);
    /**
     * 实现链接数据的批量审核,执行到此时,List集合内一定有数据
     * @param ids
     * @return
     */
    boolean doCheck(List<String> ids);

    /**
     * 分页模糊查询
     * @param title  查询关键字
     * @return  查询结果集合
     */
    List<SkLinkCn> likeSelectAll(String title);
    /**
     * 分页查询所有
     * @return 查询的内容
     */
    List<SkLinkCn> listAllLink();
}
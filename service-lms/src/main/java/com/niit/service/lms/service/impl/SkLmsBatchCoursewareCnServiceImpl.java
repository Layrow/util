package com.niit.service.lms.service.impl;


import com.niit.service.lms.dao.SkLmsBatchCoursewareCnMapper;
import com.niit.service.lms.pojo.SkLmsBatchCoursewareCn;
import com.niit.service.lms.service.SkLmsBatchCoursewareCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangwei
 * @Date: 2018/11/8 14:27
 * @Description:
 */
@Service
public class SkLmsBatchCoursewareCnServiceImpl implements SkLmsBatchCoursewareCnService {

    @Autowired
    private SkLmsBatchCoursewareCnMapper ccm;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ccm.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SkLmsBatchCoursewareCn record) {
        int i = ccm.insertSelective(record);
        return i;
    }

    @Override
    public void deleteAd(String batch_id, Integer coursewareId) {
        List<String> list = getList(batch_id);
        ccm.deleteAd(list, coursewareId);

    }

    @Override
    public List<Map<String, Object>> select(Integer coursewareId, Integer facultyId) {
        //  HashMap<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> select = ccm.select(coursewareId, facultyId);
      /*  map.put("ids",ccm.selectId(coursewareId,facultyId));
        map.put("batchInfo",ccm.selectAllBatch(facultyId));*/
        // list.add(map);
        return select;
    }

    @Override
    public int insertBatch(List<SkLmsBatchCoursewareCn> sk) {
        return ccm.insertBatch(sk);


    }

   /* @Override
    public List<Map<String, Object>> shareBatch(Integer coursewareId,Integer facultyId) {
        List<Map<String, Object>> map = ccm.shareBatch(coursewareId, facultyId);
        return map;
    }*/


    /**
     * id放入list
     *
     * @param id
     * @return
     */

    public List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;

    }


}

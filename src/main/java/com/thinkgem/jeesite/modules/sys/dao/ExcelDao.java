package com.thinkgem.jeesite.modules.sys.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Excel;

import java.util.List;

@MyBatisDao
public interface ExcelDao extends CrudDao<Excel> {
    /**
     * 基本插入
     */
    /*@Insert("insert into excel(json) values(#{json})")*/
    void addJson(String json);

    /**
     * 根据主键查询
     */
//    @Select("select json from excel")
//    String selectJsonById();
    public List<String> findTypeList(Excel dict);
}

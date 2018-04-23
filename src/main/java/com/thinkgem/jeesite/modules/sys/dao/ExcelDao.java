package com.thinkgem.jeesite.modules.sys.dao;


public interface ExcelDao {
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
}

package com.thinkgem.jeesite.modules.sys.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ExcelDao extends CrudDao<Excel> {

    /**
     * 插入
     */
    void addJson(@Param("excel_id") int excel_id,@Param("json") String json);
    /**
     * 根据主键选择
     * @return
     */
    List<Excel> selectJsonById();

    /**
     * 查询表格
     * @return
     */
      List<Excel> selectJson();

    /**
     * 根据主键查询
     */
//    @Select("select json from excel")
//    String selectJsonById();
    public List<String> findIdList(Excel excel);
    public List<Excel> findAllList(Excel excel);

}

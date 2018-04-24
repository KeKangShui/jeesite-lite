package com.thinkgem.jeesite.modules.sys.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.sys.dao.ExcelDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ExcelService extends BaseService<ExcelDao,Excel> {
    @Autowired
    private ExcelDao excelDao;

    public void addJson(String json) {
         excelDao.addJson(json);
    }

  /*  public String selectJsonById() {
        return excelDao.selectJsonById();
    }*/

    /**
     * 查询字段类型列表
     *
     * @return
     */
    public List<String> findTypeList() {
        return excelDao.findTypeList(new Excel());
    }

    @Transactional(readOnly = false)
    public void save(Excel excel) {
        super.save(excel);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(Excel excel) {
        super.delete(excel);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void batchDelete(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        super.deleteBatchIds(idList);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

}

package com.thinkgem.jeesite.modules.sys.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.dao.ExcelDao;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

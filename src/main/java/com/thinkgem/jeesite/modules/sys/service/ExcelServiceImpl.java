package com.thinkgem.jeesite.modules.sys.service;

import com.thinkgem.jeesite.common.service.ExcelService;
import com.thinkgem.jeesite.modules.sys.dao.ExcelDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private ExcelDao excelDao;

    public void addJson(String json) {
         excelDao.addJson(json);
    }

  /*  public String selectJsonById() {
        return excelDao.selectJsonById();
    }*/
}

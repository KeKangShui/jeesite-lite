package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.common.persistence.BaseEntity;

public class Excel extends BaseEntity<Excel> {
    private String id;
    private int excel_id;
    private String json;
    private String type;

    public int getExcel_id() {
        return excel_id;
    }

    public void setExcel_id(Integer excel_id) {
        this.excel_id = excel_id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}

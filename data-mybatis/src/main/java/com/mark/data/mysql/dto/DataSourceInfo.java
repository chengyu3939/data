package com.mark.data.mysql.dto;

/**
 * @author mark
 */
public class DataSourceInfo {
    private String name;
    private String type;
    private Boolean isDefault;
    private DataSourceConnect dataSourceConnect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public DataSourceConnect getDataSourceConnect() {
        return dataSourceConnect;
    }

    public void setDataSourceConnect(DataSourceConnect dataSourceConnect) {
        this.dataSourceConnect = dataSourceConnect;
    }
}

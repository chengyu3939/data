package com.mark.data.mysql.constant;

import com.mark.data.mysql.constant.DbConstant;

/**
 * @author mark
 */

public enum DataSourceEnum {
    MANAGER_MASTER_DB("manager", DbConstant.DB_TYPE_MASTER, "manager-master-db"),
    MANAGER_SLAVE_DB("manager", DbConstant.DB_TYPE_SLAVE, "manager-master-db");

    private String dbName;
    private String dbType;
    private String dbDes;

    DataSourceEnum(String dbName, String dbType, String dbDes) {
        this.dbName = dbName;
        this.dbType = dbType;
        this.dbDes = dbDes;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbDes() {
        return dbDes;
    }

    public void setDbDes(String dbDes) {
        this.dbDes = dbDes;
    }
}

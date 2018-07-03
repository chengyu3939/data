package com.mark.data.mysql.constant;

/**
 * @author mark
 */

public enum DataSourceEnum {

    MANAGER_MASTER_DB("manager", Constant.DB_TYPE_MASTER, "manager-master-db"),
    MANAGER_SLAVE_DB("manager", Constant.DB_TYPE_SLAVE, "manager-master-db");

    /**
     * 选对默认数据源
     */
    public static  DataSourceEnum defaultDataSourceEnum=DataSourceEnum.MANAGER_MASTER_DB;

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

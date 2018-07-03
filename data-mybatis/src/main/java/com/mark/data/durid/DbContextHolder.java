package com.mark.data.durid;

public class DbContextHolder {


    private static final ThreadLocal<DataSourceEnum> CONTEXTHOLDER = new ThreadLocal<>();


    public static void setDbType(DataSourceEnum dataSourceEnum) {
        CONTEXTHOLDER.set(dataSourceEnum);
    }

    public static DataSourceEnum getDbType() {
        return CONTEXTHOLDER.get();
    }

    public static void clearDbType() {
        CONTEXTHOLDER.remove();
    }
}

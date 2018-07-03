package com.mark.data.mysql.config;

import com.mark.data.mysql.constant.DataSourceEnum;

/**
 * @author mark
 */
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

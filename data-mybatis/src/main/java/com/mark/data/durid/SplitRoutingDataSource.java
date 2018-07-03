package com.mark.data.durid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SplitRoutingDataSource extends AbstractRoutingDataSource {


    private static final Logger logger = LoggerFactory.getLogger(SplitRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {

        DataSourceEnum dataSourceEnum;

        if (DbContextHolder.getDbType()==null) {

            dataSourceEnum = DataSourceEnum.MANAGER_MASTER_DB;

        }else {
            dataSourceEnum=DbContextHolder.getDbType();
        }
        logger.debug("current db :{}",dataSourceEnum);
        return dataSourceEnum;
    }
}

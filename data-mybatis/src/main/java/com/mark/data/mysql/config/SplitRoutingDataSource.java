package com.mark.data.mysql.config;

import com.mark.data.mysql.constant.DataSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author mark
 */
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

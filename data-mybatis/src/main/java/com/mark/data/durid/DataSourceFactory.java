package com.mark.data.durid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * 数据库连接配置工厂
 *
 * @author mark
 */
public class DataSourceFactory {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);
    private static Properties props;

    public static DriverManagerDataSource get(DataSourceEnum em) {
        //补充配置文件配置的properties配置
        Properties connProperties = initConnectionProperties(em);
        logger.info("初始化数据库连接配置 dataSource: {} ,properties:{}", em.getDbDes(), connProperties);
        //根据数据库和数据库类型获取配置
        //根据配置生成dataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setConnectionProperties(connProperties);
        dataSource.setUrl(getProperty(em.getDbName() + "." + em.getDbType() + ".url"));
        dataSource.setDriverClassName(getProperty(em.getDbName() + "." + em.getDbType() + ".driver-class-name"));
        dataSource.setUsername(getProperty(em.getDbName() + "." + em.getDbType() + ".username"));
        dataSource.setPassword(getProperty(em.getDbName() + "." + em.getDbType() + ".password"));
        return dataSource;
    }

    /**
     * 补充配置
     *
     * @param em
     */
    private static Properties initConnectionProperties(DataSourceEnum em) {

        try {


            Properties dataSourceConnProperties = new Properties();
            //默认加载项
            initDefaultProperties(dataSourceConnProperties);

            //用配置文件的配置项覆盖默认配置
            if (null == props) {
                loadProps();
            }
            Set<String> names = props.stringPropertyNames();
            for (String str : names) {

                String keyStartStr = em.getDbName() + "." + em.getDbType() + ".conf.";
                if (str.startsWith(keyStartStr)) {
                    //获取配置的value
                    String value = props.getProperty("str");
                    dataSourceConnProperties.setProperty(str.substring(keyStartStr.length()), value);
                }
            }
            return dataSourceConnProperties;

        } catch (Exception ex) {
            logger.error("初始化配置文件异常");
            throw ex;
        }

    }

    private static void initDefaultProperties(Properties dataSourceConnProperties) {
        dataSourceConnProperties.setProperty("filters", "stat");
        dataSourceConnProperties.setProperty("maxActive", "20");
        dataSourceConnProperties.setProperty("initialSize", "1");
        dataSourceConnProperties.setProperty("maxWait", "60000");
        dataSourceConnProperties.setProperty("minIdle", "1");
        dataSourceConnProperties.setProperty("timeBetweenEvictionRunsMillis", "60000");
        dataSourceConnProperties.setProperty("minEvictableIdleTimeMillis", "300000");
        dataSourceConnProperties.setProperty("validationQuery", "select 'x'");
        dataSourceConnProperties.setProperty("testWhileIdle", "true");
        dataSourceConnProperties.setProperty("testOnBorrow", "false");
        dataSourceConnProperties.setProperty("testOnReturn", "false");
        dataSourceConnProperties.setProperty("poolPreparedStatements", "true");
        dataSourceConnProperties.setProperty("maxOpenPreparedStatements", "20");
    }


    //配置文件读取操作

    synchronized static private void loadProps() {
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = DataSourceFactory.class.getClassLoader().getResourceAsStream("dataSource.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
    }

    private static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }

        String value = props.getProperty(key);
        logger.info("读取配置 key：{}, value:{}", key, value);
        return value;
    }

}

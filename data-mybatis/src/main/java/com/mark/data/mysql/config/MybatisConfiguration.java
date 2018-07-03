package com.mark.data.mysql.config;

import com.mark.data.mysql.constant.DataSourceEnum;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mark
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfiguration extends MybatisAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);


    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    /**
     * 重写sqlSessionFactory
     *
     * @param dataSource 数据源
     * @return
     * @throws Exception
     */
    @Override
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        return super.sqlSessionFactory(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.info("======================== Create SqlSessionFactory ========================");
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }

    /**
     * 路由dataSouce
     *
     * @return
     */
    private DataSource roundRobinDataSourceProxy() {

        SplitRoutingDataSource proxy = new SplitRoutingDataSource();


        //将所有的配置的数据源注入

        Map<Object, Object> targetDataResources;

        // TODO: 2018/4/15 实现自动注入

        /*
        两种方案，均实现
       1.通过代码识别自动加载bean
       通过代码自动注入bean

       2.无需注入bean 此处创建daaSource对象即可
        */


        //此处实现 2
        targetDataResources = combineTargetDataSource();


        proxy.setTargetDataSources(targetDataResources);
        // TODO: 2018/4/15 默认数据源设置方案 目前随意设置
        //设置默认的数据源
        proxy.setDefaultTargetDataSource(targetDataResources.get(0));
        proxy.afterPropertiesSet();//将target数据源中间取出ThreadHolder中配置的数据源
        return proxy;
    }


    /**
     * 遍历配置创建数据源
     *
     * @return 数据源列表
     */

    private Map<Object, Object> combineTargetDataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        //遍历DataSourceEnum
        DataSourceEnum.values();

        for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {

            DataSource dataSource = DataSourceFactory.get(dataSourceEnum);

            targetDataSource.put(dataSourceEnum, dataSource);
        }

        return targetDataSource;


    }
}

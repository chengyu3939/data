package com.mark.data.mysql.util;

import com.mark.data.mysql.config.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * 配置文件读取类
 * @author mark
 */
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载配置文件返回properties对象
     * @param profileName 配置文件名
     * @return 配置对象
     */
    public static Properties load(String profileName) {
        Properties props;
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = DataSourceFactory.class.getClassLoader().getResourceAsStream(profileName);
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
        return props;
    }
}

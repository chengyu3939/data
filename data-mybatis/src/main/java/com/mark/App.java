package com.mark;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mark.data.mysql.config.DataSourceFactory;
import com.mark.data.mysql.dto.DataSourceConnect;
import com.mark.data.mysql.dto.DataSourceInfo;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Yaml yaml = new Yaml();
        Map<String, Object> map = (Map<String, Object>) yaml.load(App.class.getClassLoader().getResourceAsStream("dataSource.yml"));

        JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(map));


        System.out.println(jsonObject.keySet());
        String defaultDB=null;

        List<DataSourceInfo>  resource=new ArrayList<>();


        for (String key:jsonObject.keySet()){

            if ("default".equals(key)){
                defaultDB=jsonObject.getString(key);
                continue;
            }

            JSONObject jsonObject1=jsonObject.getJSONObject(key);

            for (String key1: jsonObject1.keySet()) {
                JSONObject jsonValue=jsonObject1.getJSONObject(key1);

                DataSourceInfo dataSourceInfo=new DataSourceInfo();
                dataSourceInfo.setName(key);
                dataSourceInfo.setType(key1);

                DataSourceConnect dataSourceConnect = new DataSourceConnect();
                dataSourceConnect.setUserName(jsonValue.getString("username"));
                dataSourceConnect.setPassWord(jsonValue.getString("password"));
                dataSourceConnect.setDriverClassName(jsonValue.getString("driver-class-name"));
                dataSourceConnect.setUrl(jsonValue.getString("url"));


                dataSourceInfo.setDataSourceConnect(dataSourceConnect);

                resource.add(dataSourceInfo);


            }
        }

        for (DataSourceInfo datasource: resource) {

            String temp=datasource.getName()+":"+datasource.getType();
            if (temp.equals(defaultDB)){
                datasource.setDefault(true);
            }
        }

        System.out.println(JSON.toJSONString(resource,SerializerFeature.PrettyFormat));

    }
}

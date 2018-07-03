package com.mark.data.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public class OperationDao {
    public static void main(String[] args) {

//        private Integer id;
//        private String url;
//        private String dsUid;
//        private String transId;
//        private String requestData;
//        private String responseData;
//        private String code;
//        private String msg;

        new OperationDao().select();

    }


    public void init() {


        MongoCollection<Document> mongoCollection = MongoUtils.instance.getCollection("local", "operation");

        for (int i = 0; i < 100; i++) {
            Document document = new Document();
            document.put("url", "/user/login");
            document.put("merchantId", "50000");
            document.put("dsUid", "37087");
            document.put("transId", "OA239842979237498" + i);
            document.put("requestData", "no data");
            document.put("responseData", "no response");
            document.put("code", "0");
            document.put("msg", "success");
            mongoCollection.insertOne(document);

        }

    }


    public void select() {
        MongoCollection<Document> mongoCollection = MongoUtils.instance.getCollection("local", "operation");

        System.out.println(mongoCollection.count());


    }
}

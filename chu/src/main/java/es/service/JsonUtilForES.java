package es.service;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import es.domain.Medicine;


public class JsonUtilForES {

    /**
     * 实现将实体对象转换成json对象
     * @param medicine    Medicine对象
     * @return
     */
    public static String obj2JsonData(Medicine medicine){
        String jsonData = null;
        try {
            //使用XContentBuilder创建json数据
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild
            .field("id",medicine.getId())
            .field("name", medicine.getName())
            .field("funciton",medicine.getFunction());
            jsonData = jsonBuild.string();
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

}
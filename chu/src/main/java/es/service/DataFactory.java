package es.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import es.domain.Medicine;

public class DataFactory {
    
    public static DataFactory dataFactory = new DataFactory();
    
    private DataFactory(){
        
    }
    
    public DataFactory getInstance(){
        return dataFactory;
    }
    
    public static List<String> getInitJsonData(){
        List<String> list = new ArrayList<String>();
//        for (int i = 244790; i <= 300000; i++) {
//        	String data1  = JsonUtil.toJson(new Medicine(i,"ganmao","function"));
//        	 list.add(data1);
//		}
        String data2  = obj2JsonData(new Medicine(1000001,"美女","功能主治：感冒止咳糖浆,解表清热，止咳化痰"));
        System.err.println(data2);
        list.add(data2);
        return list;
    }
    
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
            jsonBuild.startObject()
            .field("id",medicine.getId())
            .field("name", medicine.getName())
            .field("function",medicine.getFunction()).endObject();
            jsonData = jsonBuild.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public static void main(String[] args) {
    	DataFactory.getInitJsonData();
	}
}
package com.fjyt.asset.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author keQiLong
 * @date 2023年04月17日 11:28
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${applicationCredentials.appkey}")
    private String appkey;
    @Value("${applicationCredentials.appsecret}")
    private String appsecret;

    @GetMapping("/getFormNacos")
    public ConcurrentHashMap<String,String> testNacos(){
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,String>();
        map.put("appkey",appkey);
        map.put("appsecret",appsecret);
        System.out.println("钉钉应用appkey:"+appkey+"\n"+
                "钉钉应用appsecret:"+appsecret);
        return map;
    }
    //province字典
    @PostMapping("/getProvince")
    public StringBuffer getProvince(@RequestBody List<Map<String,String>> list){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO province (name,province_id) VALUES ");
        for (Map<String, String> map : list) {
            sql.append("('"+map.get("name")+"', '"+map.get("id")+"'),");
        }
        System.out.println(sql);
        return sql;
    }
    //city字典
    @PostMapping("/getCity")
    public StringBuffer getCity(@RequestBody Map<String,List<Map<String,String>>> map){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO city (name,city_id,province_id) VALUES ");
        for (String key:map.keySet()){
            List<Map<String, String>> maps = map.get(key);
            for (Map<String, String> stringStringMap : maps) {
                sql.append("('"+stringStringMap.get("name")+"', '"+stringStringMap.get("id")+"', '"+key+"'),");
            }
        }
        System.out.println(sql);
        return sql;
    }
    //county字典
    @PostMapping("/getCounty")
    public StringBuffer getCounty(@RequestBody Map<String,List<Map<String,String>>> map){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO county (name,county_id,city_id) VALUES ");
        for (String key:map.keySet()){
            List<Map<String, String>> maps = map.get(key);
            for (Map<String, String> stringStringMap : maps) {
                sql.append("('"+stringStringMap.get("name")+"', '"+stringStringMap.get("id")+"', '"+key+"'),");
            }
        }
        System.out.println(sql);
        return sql;
    }
}

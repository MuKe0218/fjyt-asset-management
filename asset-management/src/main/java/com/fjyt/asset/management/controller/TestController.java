package com.fjyt.asset.management.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fjyt.asset.management.POJO.DTO.DingDingDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author keQiLong
 * @date 2023年04月17日 11:28
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${applicationCredentials.appKey}")
    private String appkey;
    @Value("${applicationCredentials.appSecret}")
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

    @GetMapping
    public void testWork(){
        String string = "[{\"rowValue\":[{\"label\":\"物品名称\",\"value\":\"电脑\",\"key\":\"物品名称\"},{\"label\":\"数量\",\"value\":\"2\",\"key\":\"数量\"}],\"rowNumber\":\"TableField-MINGXI_MRR2224BCB40\"},{\"rowValue\":[{\"label\":\"物品名称\",\"value\":\"椅子\",\"key\":\"物品名称\"},{\"label\":\"数量\",\"value\":\"2\",\"key\":\"数量\"}],\"rowNumber\":\"TableField-MINGXI_1HGS6SLCP7PC0\"}]";
        List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(string, DingDingDTO.class);
        dingDingDTOS.stream().forEach(dingDingDTO -> {
            List<Map<String, String>> rowValue = dingDingDTO.getRowValue();
            Map<String, String> assetNameMap = rowValue.get(0);
            Map<String, String> numMap = rowValue.get(1);
             String assetName = assetNameMap.get("value");
             int num = Integer.valueOf(numMap.get("value"));
             System.out.println(assetName);
             System.out.println(num);
        });
//        map.put("dasd","da");
//        List<Map> list = new ArrayList<>();
//        list.add(map);
//        System.out.println("map:"+map);
//        System.out.println("list:"+list);
    }
}

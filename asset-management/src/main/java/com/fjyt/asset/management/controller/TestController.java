package com.fjyt.asset.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

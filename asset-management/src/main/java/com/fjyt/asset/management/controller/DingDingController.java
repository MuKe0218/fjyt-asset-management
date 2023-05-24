package com.fjyt.asset.management.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fjyt.asset.management.service.DingDingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author keQiLong
 * @date 2023年05月19日 10:59
 */
@RestController
@RequestMapping("/DingDing")
public class DingDingController {


    @Autowired
    private DingDingService dingDingService;

    @PostMapping
    public Map<String, String> DingDingMessage(@RequestParam(value = "msg_signature", required = false) String msg_signature,
                                               @RequestParam(value = "timestamp", required = false) String timestamp,
                                               @RequestParam(value = "nonce", required = false) String nonce,
                                               @RequestBody(required = false) JSONObject json){
        return dingDingService.DingDingMessage(msg_signature,timestamp,nonce,json);
    }
}

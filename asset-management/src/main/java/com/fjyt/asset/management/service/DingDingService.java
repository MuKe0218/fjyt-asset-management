package com.fjyt.asset.management.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author keQiLong
 * @date 2023年05月19日 11:33
 */
public interface DingDingService {
    Map<String, String>  DingDingMessage(String msg_signature, String timeStamp, String nonce, JSONObject json);
}

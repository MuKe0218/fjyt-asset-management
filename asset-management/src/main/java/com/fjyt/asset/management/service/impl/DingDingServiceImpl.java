package com.fjyt.asset.management.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceResponse;
import com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.fjyt.asset.management.POJO.DO.DingDingEvent;
import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.DingDingDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.constant.Constants;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.DingDingMapper;
import com.fjyt.asset.management.service.AssetBorrowService;
import com.fjyt.asset.management.service.AssetUseService;
import com.fjyt.asset.management.service.DingDingService;
import com.fjyt.asset.management.service.ProcureService;
import com.fjyt.asset.management.utils.DateUtils;
import com.fjyt.asset.management.utils.DingCallbackCrypto;
import com.fjyt.asset.management.utils.RedisUtils;
import com.fjyt.asset.management.utils.StringUtils;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author keQiLong
 * @date 2023年05月19日 10:50
 */
@Service
public class DingDingServiceImpl implements DingDingService {

    @Value("${applicationCredentials.appKey}")
    private String appKey;
    @Value("${applicationCredentials.appSecret}")
    private String appSecret;
    @Value("${applicationCredentials.aesToken}")
    private String aesToken;
    @Value("${applicationCredentials.aesKey}")
    private String aesKey;


    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetUseService assetUseService;
    @Autowired
    private AssetBorrowService assetBorrowService;
    @Autowired
    private ProcureService procureService;
    @Autowired
    private DingDingMapper dingDingMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> DingDingMessage(String msg_signature, String timeStamp, String nonce, JSONObject json) {
        try {
            // 1. 从http请求中获取加解密参数

            // 2. 使用加解密类型
            // Constant.OWNER_KEY 说明：
            // 1、开发者后台配置的订阅事件为应用级事件推送，此时OWNER_KEY为应用的APP_KEY。
            // 2、调用订阅事件接口订阅的事件为企业级事件推送，
            //      此时OWNER_KEY为：企业的appkey（企业内部应用）或SUITE_KEY（三方应用）
            DingCallbackCrypto callbackCrypto = new DingCallbackCrypto(aesToken, aesKey, appKey);
            String encryptMsg = json.getString("encrypt");
            String decryptMsg = callbackCrypto.getDecryptMsg(msg_signature, timeStamp, nonce, encryptMsg);

            // 3. 反序列化回调事件json数据
            JSONObject eventJson = JSON.parseObject(decryptMsg);
            String eventType = eventJson.getString("EventType");

            // 4. 根据EventType分类处理
            System.out.println(eventType);
            // 判断实例是否为OA审批
            if ("bpms_instance_change".equals(eventType)){
                String title = eventJson.getString("title");
                String module = title.substring(title.indexOf("的")+1,title.length());
                System.out.println(title);
                System.out.println(module);
                System.out.println(eventJson.getString("processInstanceId"));
                System.out.println(eventJson.getString("type"));
                if (Constants.ZC_USE.equals(module)){
                    System.out.println("领用");
                    UseWork(eventJson);
                } else if (Constants.ZC_BORROW.equals(module)){
                    System.out.println("借用");
                    BorrowWork(eventJson);
                }else if (Constants.ZC_PROCURE.equals(module)){
                    System.out.println("采购");
                    ProcureWork(eventJson);
                }
            } else if ("check_url".equals(eventType)){
                getAccessToken();
            }
            // 5. 返回success的加密数据
            Map<String, String> successMap = callbackCrypto.getEncryptedMap("success");
            return successMap;

        } catch (DingCallbackCrypto.DingTalkEncryptException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取accessToken
    public void getAccessToken(){
        try {
            System.out.println("appKey: "+appKey);
            System.out.println("appSecret: "+appSecret);
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey(appKey);
            req.setAppsecret(appSecret);
            req.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(req);
            System.out.println(rsp.getAccessToken());
            redisUtils.setCacheObject("access_token:token",rsp.getAccessToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static com.aliyun.dingtalkworkflow_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkworkflow_1_0.Client(config);
    }
    /**
     * 获取审批实例
     */
    public List<GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues> getSingleApprove(String processInstanceId) throws Exception {
        List<GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues> formComponentValues = new ArrayList<>();
        String accessToken = redisUtils.getCacheObject("access_token:token");
        com.aliyun.dingtalkworkflow_1_0.Client client = this.createClient();
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders getProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders();
        getProcessInstanceHeaders.xAcsDingtalkAccessToken = accessToken;
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest getProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest();
        getProcessInstanceRequest.setProcessInstanceId(processInstanceId);
        try {
            GetProcessInstanceResponse processInstanceWithOptions = client.getProcessInstanceWithOptions(getProcessInstanceRequest, getProcessInstanceHeaders, new RuntimeOptions());
            System.out.println("结果1："+processInstanceWithOptions.toMap().get("body"));
            formComponentValues = processInstanceWithOptions.getBody().getResult().getFormComponentValues();
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        }
        return formComponentValues;
    }
    /**
     * 领用操作
     */
    public void UseWork(JSONObject eventJson) throws Exception {
        String type = eventJson.getString("type");
        String processInstanceId = eventJson.getString("processInstanceId");
        String result = eventJson.getString("result");
        String title = eventJson.getString("title");
        String useUser = title.substring(0,title.indexOf("提"));

        String resultCode = dingDingMapper.getCode(processInstanceId);
        System.out.println("resultCode: "+resultCode);
        System.out.println("StringUtils.isNotNull(resultCode):"+StringUtils.isNotNull(resultCode));
        //  判断审批状态是否为开始 同时判断事件是否已保存
        if("start".equals(type) && !StringUtils.isNotNull(resultCode)){
            // 根据processInstanceId调用钉钉api获取审批实例信息 保存到数据库中
            List<GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues> singleApprove = getSingleApprove(processInstanceId);
            // 保存事件信息
            DingDingEvent dingDingEvent = new DingDingEvent();
            dingDingEvent.setProcessInstanceId(processInstanceId);
            dingDingEvent.setMessage(singleApprove.toString());
            dingDingEvent.setTime(DateUtils.getNowDate());
            dingDingMapper.addDingDingEven(dingDingEvent);

            GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues getProcessInstanceResponseBodyResultFormComponentValues = singleApprove.get(7);
            String value = getProcessInstanceResponseBodyResultFormComponentValues.getValue();
            System.out.println(value);
            List<String> list = new ArrayList<>();
//            List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(value, DingDingDTO.class);
//            dingDingDTOS.stream().forEach(dingDingDTO -> {
//                List<Map<String, String>> rowValue = dingDingDTO.getRowValue();
//                Map<String, String> assetNameMap = rowValue.get(0);
//                Map<String, String> numMap = rowValue.get(1);
//                String assetName = assetNameMap.get("value");
//                int num = Integer.valueOf(numMap.get("value"));
//                System.out.println("物品名称："+assetName+"；数量："+num);
//                List<String> assetCodeByAssetName = assetMapper.getAssetCodeByAssetName(assetName, num);
//                list.addAll(assetCodeByAssetName);
//            });
            String assetName = value;
            int num = Integer.valueOf(singleApprove.get(9).getValue());
            List<String> assetCodeByAssetName = assetMapper.getAssetCodeByAssetName(assetName, num);
            list.addAll(assetCodeByAssetName);
            // 关联创建领用单，并将领用单状态设置为审批中
            assetUseService.addUseFromDingDing(list,useUser,processInstanceId);
        }else if ("finish".equals(type) && StringUtils.isNotNull(resultCode)){
            String code = dingDingMapper.getCode(processInstanceId);
            String codes = assetUseService.getCodes(code).replace(" ","");
            if (StringUtils.isNotNull(codes)){
                String[] split = codes.split(",");
                List<String> list = Arrays.asList(split);
                UpdateStatus updateStatus = new UpdateStatus();
                //判断审批是否同意
                if ("agree".equals(result)){
                    // 将领用单状态变为已领用
                    assetUseService.updateStatus("0",processInstanceId);
                    // 将领用的资产状态变为领用状态，并更改库存
                    updateStatus.setStatus("1");
                    updateStatus.setCollect(list);
                    assetMapper.updateStatusList(updateStatus);
                }else if ("refuse".equals(result)){
                    // 将领用单状态变为审批未通过
                    assetUseService.updateStatus("2",processInstanceId);
                    // 将领用资产状态变为空闲
                    updateStatus.setStatus("0");
                    updateStatus.setCollect(list);
                    assetMapper.updateStatusList(updateStatus);
                }else {
                    System.out.println("别的结果："+result);
                }
            }
        }else{
            System.out.println("其他状态："+type);
        }
    }
    /**
     * 借用操作
     */
    private void BorrowWork(JSONObject eventJson) throws Exception {
        String type = eventJson.getString("type");
        String processInstanceId = eventJson.getString("processInstanceId");
        String result = eventJson.getString("result");
        String title = eventJson.getString("title");
        String borrowUser = title.substring(0,title.indexOf("提"));

        String resultCode = dingDingMapper.getCode(processInstanceId);
        // 判断审批状态是否为开始 同时判断事件是否已保存
        if("start".equals(type) && !StringUtils.isNotNull(resultCode)){
            //根据processInstanceId调用钉钉api获取审批实例信息 保存到数据库中
            List<GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues> singleApprove = getSingleApprove(processInstanceId);
            // 保存事件信息
            DingDingEvent dingDingEvent = new DingDingEvent();
            dingDingEvent.setProcessInstanceId(processInstanceId);
            dingDingEvent.setMessage(singleApprove.toString());
            dingDingEvent.setTime(DateUtils.getNowDate());
            dingDingMapper.addDingDingEven(dingDingEvent);

            GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues getProcessInstanceResponseBodyResultFormComponentValues = singleApprove.get(1);
            String value = getProcessInstanceResponseBodyResultFormComponentValues.getValue();
            System.out.println(value);
            List<String> list = new ArrayList<>();
            List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(value, DingDingDTO.class);
            dingDingDTOS.stream().forEach(dingDingDTO -> {
                List<Map<String, String>> rowValue = dingDingDTO.getRowValue();
                Map<String, String> assetNameMap = rowValue.get(3);
                Map<String, String> numMap = rowValue.get(4);
                String assetName = assetNameMap.get("value");
                int num = Integer.valueOf(numMap.get("value"));
                System.out.println("物品名称："+assetName+"；数量："+num);
                List<String> assetCodeByAssetName = assetMapper.getAssetCodeByAssetName(assetName, num);
                list.addAll(assetCodeByAssetName);
            });
            // 关联创建借用单，并将借用单状态变为审批中
            assetBorrowService.addBorrowFromDingDing(list,borrowUser,processInstanceId);
        }else if ("finish".equals(type) && StringUtils.isNotNull(resultCode)){
            String code = dingDingMapper.getCode(processInstanceId);
            String codes = assetBorrowService.getCodes(code).replace(" ","");
            if (StringUtils.isNotNull(codes)){
                String[] split = codes.split(",");
                List<String> list = Arrays.asList(split);
                UpdateStatus updateStatus = new UpdateStatus();
                //判断审批是否同意
                if ("agree".equals(result)){
                    // 将借用单状态
                    assetBorrowService.updateStatus("0",processInstanceId);
                    // 将借用的资产状态变已借用，并更改库存
                    updateStatus.setStatus("2");
                    updateStatus.setCollect(list);
                    assetMapper.updateStatusList(updateStatus);
                }else if ("refuse".equals(result)){
                    // 将借用单状态变为审批未通过
                    assetBorrowService.updateStatus("2",processInstanceId);
                    // 将借用资产状态变为空闲
                    updateStatus.setStatus("0");
                    updateStatus.setCollect(list);
                    assetMapper.updateStatusList(updateStatus);
                }else{
                    System.out.println("别的结果："+result);
                }
            }
        }else {
            System.out.println("其他状态："+type);
        }
    }
    /**
     * 采购处理
     */
    private void ProcureWork(JSONObject eventJson) throws Exception {
        String type = eventJson.getString("type");
        String processInstanceId = eventJson.getString("processInstanceId");
        String result = eventJson.getString("result");
        String title = eventJson.getString("title");
        String procureUser = title.substring(0,title.indexOf("提"));
        String resultCode = dingDingMapper.getCode(processInstanceId);
        // 判断审批状态是否为开始 同时判断事件是否已保存
        if("start".equals(type)  && !StringUtils.isNotNull(resultCode)){
            //根据processInstanceId调用钉钉api获取审批实例信息 保存到数据库中
            List<GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues> singleApprove = getSingleApprove(processInstanceId);
            // 保存事件信息
            DingDingEvent dingDingEvent = new DingDingEvent();
            dingDingEvent.setProcessInstanceId(processInstanceId);
            dingDingEvent.setMessage(singleApprove.toString());
            dingDingEvent.setTime(DateUtils.getNowDate());
            dingDingMapper.addDingDingEven(dingDingEvent);

            GetProcessInstanceResponseBody.GetProcessInstanceResponseBodyResultFormComponentValues getProcessInstanceResponseBodyResultFormComponentValues = singleApprove.get(4);
            String value = getProcessInstanceResponseBodyResultFormComponentValues.getValue();
            System.out.println(value);
            Procure procure = new Procure();
            procure.setProcureAssets(value);
            procure.setProcureUser(procureUser);
            String flag = singleApprove.get(8).getValue();
            if (flag.equals("是")){
                procure.setRemark(singleApprove.get(16).getValue());
            }else{
                procure.setRemark(singleApprove.get(11).getValue());
            }
            // 创建采购单,并将采购状态变为审批中
            procureService.addProcureByDingDing(procure,processInstanceId);
        }else if ("finish".equals(type)  && StringUtils.isNotNull(resultCode)){
            //判断审批是否同意
            if ("agree".equals(result)){
                // 将采购单状态变为审批通过
                procureService.updateStatus("2",processInstanceId);
            }else if ("refuse".equals(result)){
                procureService.updateStatus("1",processInstanceId);
                // 将采购单状态变为审批未通过
            }else{
                System.out.println("别的结果："+result);
            }
        }else {
            System.out.println("其他状态："+type);
        }
    }
}

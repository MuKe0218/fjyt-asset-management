package com.fjyt.asset.management.utils;

import com.fjyt.asset.management.constant.SerialNumberConstants;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月05日 14:15
 * 流水号工具类
 */
public class SerialNumberUtils {

    public RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);

    /**
     * 创建流水号
     * @param prefix
     * @return
     */
    public  String createSerialNumber(String prefix){
        // RedisUtils redisUtils = new RedisUtils();
        // 流水号格式 相应前缀 + 日期 + 五位
        StringBuffer serialNumber = new StringBuffer();
        // 通过时间工具类获取时间，格式为yyyyMMdd
        String date = DateUtils.dateTime();
        // 通过时间工具类获取时间，格式为yyyy-MM-dd 用于比较
        String dateWithUnderline = DateUtils.getDate();
        // 初始化后缀五位数00000
        String suffix = "00000";
        Boolean aBoolean = redisUtils.hasKey(prefix+"_"+SerialNumberConstants.SERIAL_NUMBER_DATE);
        if (aBoolean){
            Date nowDate = DateUtils.parseDate(dateWithUnderline);
            Date oldDate = DateUtils.parseDate(redisUtils.getCacheObject(prefix+"_"+SerialNumberConstants.SERIAL_NUMBER_DATE));
            if (!nowDate.after(oldDate)){
                String redisSuffix = redisUtils.getCacheObject(prefix+"_"+SerialNumberConstants.SERIAL_NUMBER_NUM);
                int intSuffix = Integer.parseInt(redisSuffix) + 1;
                suffix = serialNumberSuffix(intSuffix);
            }
        }
        redisUtils.setCacheObject(prefix+"_"+SerialNumberConstants.SERIAL_NUMBER_DATE,dateWithUnderline);
        redisUtils.setCacheObject(prefix+"_"+SerialNumberConstants.SERIAL_NUMBER_NUM,suffix);
        serialNumber.append(prefix).append(date).append(suffix);
        return serialNumber.toString();
    }

    public static String serialNumberSuffix(int intSuffix){
        String suffix = String.valueOf(intSuffix);
        StringBuffer stringBuffer = new StringBuffer();
        switch (suffix.length()){
            case 1:
                stringBuffer.append("0000").append(suffix);
                break;
            case 2:
                stringBuffer.append("000").append(suffix);
                break;
            case 3:
                stringBuffer.append("00").append(suffix);
                break;
            case 4:
                stringBuffer.append("0").append(suffix);
                break;
            default:
                stringBuffer.append(suffix);
                break;
        }
        return stringBuffer.toString();
    }
}

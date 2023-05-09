package com.fjyt.asset.management.utils;

import com.fjyt.asset.management.enums.AssetCreateWayEnum;
import com.fjyt.asset.management.enums.AssetStatusEnum;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author keQiLong
 * @date 2023年05月05日 10:41
 * 资产工具类
 */
public class AssetUtils {
    /**
     * 资产状态信息封装
     * @return
     */
    public  static List<Map<String,String>> statusList(){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (int i=0 ;i<6;i++){
            Map map = new Hashtable();
            String label = null;
            String value = null;
            String type = null;
            switch (i){
                case 0:
                    value = AssetStatusEnum.IDLE.getCode();
                    label = AssetStatusEnum.IDLE.getInfo();
                    type = "";
                    break;
                case 1:
                    value = AssetStatusEnum.USE.getCode();
                    label = AssetStatusEnum.USE.getInfo();
                    type = "success";
                    break;
                case 2:
                    value = AssetStatusEnum.BORROW.getCode();
                    label = AssetStatusEnum.BORROW.getInfo();
                    type = "success";
                    break;
                case 3:
                    value = AssetStatusEnum.EXAMINE_AND_APPROVE.getCode();
                    label = AssetStatusEnum.EXAMINE_AND_APPROVE.getInfo();
                    type = "danger";
                    break;
                case 4:
                    value = AssetStatusEnum.MAINTENANCE.getCode();
                    label = AssetStatusEnum.MAINTENANCE.getInfo();
                    type = "warning";
                    break;
                default:
                    value = AssetStatusEnum.SCRAP.getCode();
                    label = AssetStatusEnum.SCRAP.getInfo();
                    type = "info";
                    break;
            }
            map.put("value",value);
            map.put("label",label);
            map.put("type",type);
            list.add(map);
        }
        return list;
    }

    /**
     * 创建方式封装
     * @return
     */
    public static List<Map<String,String>> createWayList(){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (int i=0 ;i<2;i++){
            Map map = new Hashtable();
            String label = null;
            String value = null;
            switch (i){
                case 0:
                    value = AssetCreateWayEnum.HAND_MOVEMENT.getCode();
                    label =  AssetCreateWayEnum.HAND_MOVEMENT.getInfo();
                    break;
                default:
                    value = AssetCreateWayEnum.FROM_DINGING.getCode();
                    label = AssetCreateWayEnum.FROM_DINGING.getInfo();
                    break;
            }
            map.put("value",value);
            map.put("label",label);
            list.add(map);
        }
        return list;
    }
}

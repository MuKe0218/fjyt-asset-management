package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月19日 17:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementDingDing {
    /**
     * 领用/借用/采购等等等code
     */
    private String code;
    /**
     * 审批实例Id
     */
    private String processInstanceId;
}

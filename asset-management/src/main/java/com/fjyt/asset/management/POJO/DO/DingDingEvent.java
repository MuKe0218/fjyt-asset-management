package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月19日 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DingDingEvent {
    /**
     * id
     */
    private Long id;
    /**
     * 钉钉审批实例id
     */
    private String processInstanceId;
    /**
     * 事件信息
     */
    private String message;
    /**
     * 时间
     */
    private Date time;
}

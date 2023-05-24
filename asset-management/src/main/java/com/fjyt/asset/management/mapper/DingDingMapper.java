package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.DingDingEvent;
import com.fjyt.asset.management.POJO.DO.ManagementDingDing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author keQiLong
 * @date 2023年05月19日 17:48
 */
@Mapper
public interface DingDingMapper {

    /**
     * 新增钉钉事件
     * @param dingDingEvent
     */
    void addDingDingEven(DingDingEvent dingDingEvent);

    /**
     * 新增领用单和审批单关联
     * @param managementDingDing
     */
    void addManagementDingDing(ManagementDingDing managementDingDing);

    /**
     * 获取对应审批单的code
     */
    String getCode(@Param("processInstanceId") String processInstanceId);
}

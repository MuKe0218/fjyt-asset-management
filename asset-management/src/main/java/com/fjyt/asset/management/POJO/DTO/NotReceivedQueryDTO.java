package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月18日 10:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotReceivedQueryDTO {
    /**
     * 资产名称
     */
    private String assetName;
}

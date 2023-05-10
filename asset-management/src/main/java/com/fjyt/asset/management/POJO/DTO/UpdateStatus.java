package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月10日 11:31
 * 修改资产状态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatus {
    private String status;
    private List<String> collect;
}

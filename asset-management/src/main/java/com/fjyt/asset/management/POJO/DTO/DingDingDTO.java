package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author keQiLong
 * @date 2023年05月23日 11:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DingDingDTO {
    private List<Map<String,String>> rowValue;
    private String rowNumber;
}

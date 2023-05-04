package com.fjyt.asset.management.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月28日 8:45
 * 分页查询返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;
}

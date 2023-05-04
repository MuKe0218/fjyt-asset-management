package com.fjyt.asset.management.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月04日 11:37
 * 分类异常处理类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassifyException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;
}

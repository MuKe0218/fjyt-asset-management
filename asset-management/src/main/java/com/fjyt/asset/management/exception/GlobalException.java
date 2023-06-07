package com.fjyt.asset.management.exception;

import com.fjyt.asset.management.POJO.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:38
 * 将异常信息发给前端
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalException {
    @ExceptionHandler(ClassifyException.class)
    public R exceptionHandler(ClassifyException exception){
        return R.fail(exception.getCode(),exception.getMessage());
    }
    @ExceptionHandler(WarehouseException.class)
    public R exceptionHandler(WarehouseException exception){
        return R.fail(exception.getCode(),exception.getMessage());
    }
    @ExceptionHandler(AssetException.class)
    public R exceptionHandler(AssetException exception){
        return R.fail(exception.getCode(),exception.getMessage());
    }
}

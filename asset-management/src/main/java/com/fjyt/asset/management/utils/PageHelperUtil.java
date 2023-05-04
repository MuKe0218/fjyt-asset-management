package com.fjyt.asset.management.utils;

import com.fjyt.asset.management.POJO.TableDataInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月28日 8:51
 * 分页查询工具类
 */
public class PageHelperUtil {
    //查询分页返回数据封装
    public TableDataInfo resultInfo(List list){
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());
        return tableDataInfo;
    }
}

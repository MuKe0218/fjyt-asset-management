package com.fjyt.asset.management.utils;

import com.fjyt.asset.management.constant.TokenConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author keQiLong
 * @date 2023年05月04日 9:27
 * token工具类
 */
public class TokenUtils {
    /**
     * 获取请求token
     */
    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }
    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request)
    {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 裁剪token前缀
     */
    public static String replaceTokenPrefix(String token)
    {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }
}

package com.sky.utils;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/13  15:47
 * @description : 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}

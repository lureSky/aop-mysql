package com.martin.config;

import com.martin.enums.DataSourceKey;
import lombok.extern.slf4j.Slf4j;

/**
 *  处理多线程访问变量问题，
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal contextHolder = new ThreadLocal();


    /**
     * @Description: 设置数据源
     * @return:
     * @author: martin
     * @date: 2020-04-09 17:59
     */
    public static void setDbType(DataSourceKey dbType){
        contextHolder.set(dbType.getValue());
    }

    /**
     * @Description: 获取当前数据源
     * @return:
     * @author: martin
     * @date: 2020-04-09 17:59
     */
    public static String getDbType(){
        return (String) contextHolder.get();
    }

    /**
     * @Description: 清除上下文对象
     * @return:
     * @author: martin
     * @date: 2020-04-09 17:59
     */
    public static void clearDbType(){
        contextHolder.remove();
    }

    //可以在这里设置随机数访问不同数据库
}

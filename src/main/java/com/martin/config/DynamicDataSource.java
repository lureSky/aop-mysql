package com.martin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/9 0009
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * @Description: 获取当前使用的是哪个数据源
     * @return: java.lang.Object
     * @author: martin
     * @date: 2020-04-09 18:03
    */
    protected Object determineCurrentLookupKey() {
        log.info("当前的数据源为：{}",DynamicDataSourceContextHolder.getDbType());
        return DynamicDataSourceContextHolder.getDbType();
    }
}

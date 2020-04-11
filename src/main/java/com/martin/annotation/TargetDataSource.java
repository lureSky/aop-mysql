package com.martin.annotation;

import com.martin.enums.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，通过注解进入项目，也是通过注解使用默认数据源
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
//目标区域是方法,运行时标签
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    DataSourceKey dataSourceKey() default DataSourceKey.master;
}

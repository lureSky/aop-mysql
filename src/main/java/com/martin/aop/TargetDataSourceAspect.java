package com.martin.aop;

import com.baomidou.mybatisplus.annotation.DbType;
import com.martin.annotation.TargetDataSource;
import com.martin.config.DynamicDataSourceContextHolder;
import com.martin.enums.DataSourceKey;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源处理类AOP
 *
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Slf4j
@Component
@Aspect
@Order(-1)
public class TargetDataSourceAspect {

    //在所有service层的list方法作为切点
    @Pointcut("execution(* com.martin.service.*.list*(..))")
    public void pointCut() {
    }

    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @Before("@annotation(targetDataSource)")
    public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        //默认是master
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();
        if (dataSourceKey == DataSourceKey.slave) {
            log.info(String.format("设置数据源为  %s", DataSourceKey.slave));
            DynamicDataSourceContextHolder.setDbType(DataSourceKey.slave);
        } else {
            log.info(String.format("使用默认数据源  %s", DataSourceKey.master));
            DynamicDataSourceContextHolder.setDbType(DataSourceKey.master);
        }
    }

    /**
     * 执行方法后清除数据源设置
     *
     * @param joinPoint        切点
     * @param targetDataSourceAspect 动态数据源
     */
    @After("@annotation(targetDataSource)")
    public void doAfter(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        log.info(String.format("当前数据源  %s  执行清理方法", targetDataSource.dataSourceKey()));
        DynamicDataSourceContextHolder.clearDbType();
    }

    //在切点方法之前判断是否存在标签，如果不存在就默认master
    @Before(value = "pointCut()")
    public void doBeforeWithSlave(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {//判断是否为接口方法
            try {
                //获取实际类型的方法对象
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                log.error("方法不存在！", e);
            }
        }
        if (null == method.getAnnotation(TargetDataSource.class)) {
            DynamicDataSourceContextHolder.setDbType(DataSourceKey.master);
        }
    }
}


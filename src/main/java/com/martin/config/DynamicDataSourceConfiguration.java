package com.martin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.martin.enums.DataSourceKey;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置数据源
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Configuration
public class DynamicDataSourceConfiguration {

    //定义master
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }

    //定义slave
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @Description: 核心动态数据源
     * @return:
     * @author: martin
     * @date: 2020-04-11 14:16
    */
    @Bean
    @Primary
    public DataSource dynamicDataSource(@Qualifier("master") DataSource master, @Qualifier("slave") DataSource slave){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master);
        //设置指定数据源
        Map<Object,Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceKey.master.getValue(), master);
        targetDataSources.put(DataSourceKey.slave.getValue(), slave);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    //设置sessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource(master(),slave()));
        //配置xml文件读取位置
        //指明mapper.xml的位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapping/**Mapper.xml"));
        //指明实体类扫描
        sqlSessionFactoryBean.setTypeAliasesPackage("com.martin.entity");
        return sqlSessionFactoryBean.getObject();
    }

    //获取sqlSessionFactory对象，并使用
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    //事务管理
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource(master(),slave()));
    }

}

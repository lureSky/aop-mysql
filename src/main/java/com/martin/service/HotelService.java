package com.martin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.martin.annotation.TargetDataSource;
import com.martin.config.DynamicDataSourceContextHolder;
import com.martin.entity.BaseInfo;
import com.martin.entity.UserInfo;
import com.martin.enums.DataSourceKey;
import com.martin.mapper.BaseInfoMapper;
import com.martin.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Slf4j
@Service
public class HotelService{

    @Resource
    private BaseInfoMapper baseInfoMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @TargetDataSource(dataSourceKey = DataSourceKey.master)
    public void testMaster(){
        //使用db1
        BaseInfo baseInfo = baseInfoMapper.selectById(1);
        int baseCount = baseInfoMapper.listCount();

        log.info("db1====================baseInfo:{},count:{}",baseInfo,baseCount);


    }

    @TargetDataSource(dataSourceKey = DataSourceKey.slave)
    public void testSlave(){
        //使用db2
        UserInfo userInfo = userInfoMapper.selectById(1);
        int userCount = userInfoMapper.listCount();

        log.info("slave====================userInfo:{},userCount:{}",userInfo,userCount);
    }
}

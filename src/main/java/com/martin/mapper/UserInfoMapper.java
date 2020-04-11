package com.martin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    //自定义方法
    int listCount();
}

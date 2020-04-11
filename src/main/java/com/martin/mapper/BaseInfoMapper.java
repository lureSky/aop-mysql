package com.martin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.martin.entity.BaseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Mapper
public interface BaseInfoMapper extends BaseMapper<BaseInfo> {
    //自定义方法
    int listCount();
}

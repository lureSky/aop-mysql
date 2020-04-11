package com.martin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Getter
@Setter
@ToString
@TableName("base_info")
public class BaseInfo implements Serializable {

    @TableId
    private int id;
    private String nickName;
    private Date createTime;
}

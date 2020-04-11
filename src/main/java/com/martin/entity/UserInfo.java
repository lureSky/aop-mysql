package com.martin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
@Getter
@Setter
@ToString
@TableName("user_info")
public class UserInfo implements Serializable {
    @TableId
    private int id;
    private String userName;
    private String passWord;
}

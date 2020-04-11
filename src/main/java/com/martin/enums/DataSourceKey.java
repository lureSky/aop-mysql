package com.martin.enums;

/**
 * @author martin
 * @email necaofeng@foxmail.com
 * @Date 2020/4/11 0011
 */
public enum DataSourceKey {
    master("master"),
    slave("slave");

    private String value;
    DataSourceKey(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}

package com.laurdawn.website.enums;

/** 
* @author  laurdawn 
* @version 2019年1月2日 上午11:18:18 
*/
public enum EResultType {

    /**
     * 访问成功返回
     */
    SUCCESS(1, "success"),

    /**
     * key数据不存在返回
     */
    KEY_NOT_FOUND(-1, "key [数据不存在 或者 数据为空]"),
    
    /**
     * value数据不存在返回
     */
    VALUE_NOT_FOUND(-1, "value [数据不存在 或者 数据为空]"),
    
    /**
     * 重复key
     */
    REPEAT_KEY(-1, "当前key已存在"),

    /**
     * 异常返回
     */
    ERROR(-1, "error [未知异常]"),

    /**
     * 参数有异常返回
     */
    GLOABLE_ERROR(-1, "全局异常"),

    /**
     * 参数有异常返回
     */
    PARAMETER_ERROR(-1, "parameter error [参数异常:参数为空或者参数类型不符]");

    private Integer code;

    private String msg;

    private EResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
	
}

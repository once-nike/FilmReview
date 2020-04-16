package com.nike.douye.Enum;

public enum Code {

    //成功
    SUCCESS(1000),
    //参数过长
    PARAM_TOO_LONG(2000),
    //参数过短
    PARAM_TOO_SHORT(2001),
    //参数格式错误
    PARAM_FORMAT_ERROR(2002),
    //参数缺失
    PARAM_MISSING(2003),
    //参数错误
    PARAM_ERROR(2004),
    //参数重复
    PARAM_REPEATED(2005),
    //参数已存在
    PARAM_ALREAD_EXIST(2006),
    //无数据
    DATA_NOT_EXIT(2007);
    private final Integer value;

    Code(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

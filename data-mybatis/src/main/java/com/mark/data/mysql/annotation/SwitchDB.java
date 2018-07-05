package com.mark.data.mysql.annotation;

import com.mark.data.mysql.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface SwitchDB {
    //db名
    String name();
    //数据库属性
    String type() default Constant.MASTER;
}

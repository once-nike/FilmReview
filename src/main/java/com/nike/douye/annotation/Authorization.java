package com.nike.douye.annotation;


import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * 用户校验
 * @author jxie
 * @since 0.0.1
 */
@NameBinding
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorization {

}

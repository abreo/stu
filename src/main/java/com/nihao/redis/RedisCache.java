package com.nihao.redis;

import java.lang.annotation.*;

/**
 * Created by nihao on 16/10/31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {
    Class type();
}

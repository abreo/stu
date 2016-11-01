package com.nihao.dao;

import com.nihao.model.Test;
import com.nihao.redis.RedisCache;
import com.nihao.redis.RedisEvict;

public interface TestMapper {
    @RedisCache(type = Test.class)
    Test selectById(String id);

    @RedisEvict(type = Test.class)
    int deleteById(String id);

    @RedisEvict(type = Test.class)
    int update(Test test);

    int insert(Test test);
}
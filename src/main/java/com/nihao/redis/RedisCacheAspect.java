package com.nihao.redis;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by nihao on 16/10/31.
 */
@Aspect
@Component
public class RedisCacheAspect {
    private org.slf4j.Logger infoLog = org.slf4j.LoggerFactory.getLogger(RedisCacheAspect.class);

    @Qualifier("java4RedisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用;
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.nihao.dao.TestMapper.select*(..))")
    //@Around("@annotation(com.nihao.redis.RedisCache)")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {
        // 得到类名、方法名和参数
        String clazzName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        // 根据类名，方法名和参数生成key
        String key = genKey(clazzName, methodName, args);
        if (infoLog.isDebugEnabled()) {
            infoLog.debug("生成key:{}", key);
        }

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = me.getAnnotation(RedisCache.class).type();

        // 检查redis中是否有缓存
        String value = (String)redisTemplate.opsForHash().get(modelType.getName(), key);

        // result是方法的最终返回结果
        Object result = null;
        if (null == value) {
            // 缓存未命中
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("缓存未命中");
            }

            // 调用数据库查询方法
            result = jp.proceed(args);

            // 序列化查询结果
            String json = serialize(result);

            // 序列化结果放入缓存
            redisTemplate.opsForHash().put(modelType.getName(), key, json);
        } else {
            // 缓存命中
            if (infoLog.isDebugEnabled()) {
                infoLog.debug("缓存命中, value = {}", value);
            }

            // 得到被代理方法的返回值类型
            Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();

            // 反序列化从缓存中拿到的json
            result = deserialize(value, returnType, modelType);

            if (infoLog.isDebugEnabled()) {
                infoLog.debug("反序列化结果 = {}", result);
            }
        }

        return result;
    }

    /**
     * 在方法调用前清除缓存，然后调用业务方法
     * @param jp
     * @return
     * @throws Throwable
     */
    //@Around("execution(* com.nihao.dao.TestMapper.update*(..))"+
      //      "|| execution(* com.nihao.dao.TestMapper.delete*(..))")
    @Around("@annotation(com.nihao.redis.RedisEvict)")
    public Object evictCache(ProceedingJoinPoint jp) throws Throwable {

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = me.getAnnotation(RedisEvict.class).type();

        if (infoLog.isDebugEnabled()) {
            infoLog.debug("清空缓存:{}", modelType.getName());
        }

        // 清除对应缓存
        redisTemplate.delete(modelType.getName());

        return jp.proceed(jp.getArgs());
    }



    /**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    protected String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(":");
        sb.append(methodName);
        sb.append(":");

        for (Object obj : args) {
            sb.append(obj.toString());
            sb.append(":");
        }

        return sb.toString();
    }

    protected String serialize(Object target) {
        return JSON.toJSONString(target);
    }

    protected Object deserialize(String jsonString, Class clazz, Class modelType) {
        // 序列化结果应该是List对象
        if (clazz.isAssignableFrom(List.class)) {
            return JSON.parseArray(jsonString, modelType);
        }

        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}
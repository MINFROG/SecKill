package com.yhp.seckill.bo.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yhp.seckill.bo.CacheBo;

/**
 * @desc 缓存实现
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年10月20日下午5:28:30
 */
@Service
public class CacheBoImpl implements CacheBo {

	
    private final static Logger logger=LoggerFactory.getLogger(CacheBoImpl.class);
    
    private static final Long exipredTime = 60*24*30L;  //一个月
	
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
	
	@Override
	public void set(String key, Object value) {
		Long startTime = System.currentTimeMillis();
		try{
			redisTemplate.opsForValue().set(key, value, exipredTime,TimeUnit.MINUTES);
		}catch(Exception e){
			logger.error("redis set error!!! key ={}, value={},cost={}ms",new Object[]{key,value,System.currentTimeMillis()-startTime,e});
		}
	}
	
	@Override
	public Object get(String key) {
		Long startTime = System.currentTimeMillis();
		try{
			return redisTemplate.opsForValue().get(key);
		}catch(Exception e){
			long cost = System.currentTimeMillis()-startTime;
			logger.error("redis get error!!! key ={},cost ={}ms!",new Object[]{key,cost,e});
			return null;
		}
	}

	@Override
	public boolean delete(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(String key, Object value,int seconds) {
		
	}
	


}


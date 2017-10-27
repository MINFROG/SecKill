package com.yhp.seckill.bo;


/**
 * @desc 缓存Bo
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年10月20日下午5:28:01
 */
public interface CacheBo {



	/**
	 * 向缓存服务存放数据
	 * @param key	
	 * @param value	
	 * @param seconds	在缓存中的失效时间，以秒为单位
	 * @return
	 */
	public void set(String key, Object value, int seconds);
	
	/**
	 * 向缓存服务存放数据
	 * @param key	
	 * @param value	
	 * @return
	 */
	public void set(String key, Object value);
	
	/**
	 * 删除缓存中指定的数据
	 * @param key
	 * @return
	 */
	public boolean delete(String key);
	
	/**
	 * 根据key从缓存中获取数据
	 * @param key
	 * @return 当获取出现异常时返回null
	 */
	public Object get(String key);
	

}


package com.yhp.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.yhp.seckill.bo.impl.KryoRedisSerializer;

/**
 * @desc 启动类
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月25日下午1:47:25
 */
@EnableAutoConfiguration
@SpringBootApplication
public class ItemSeckillApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ItemSeckillApplication.class, args);
	}
	
	
   @Bean
   public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory);

		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		KryoRedisSerializer<Object> kryoSerializer = new KryoRedisSerializer<Object>();
		template.setValueSerializer(kryoSerializer);
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
   }
}


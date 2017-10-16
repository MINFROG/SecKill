package com.yhp.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}


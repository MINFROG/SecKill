package com.yhp.seckill.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yhp.seckill.ItemSeckillApplication;

/**
 * @desc TODO添加描述
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日上午10:05:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ItemSeckillApplication.class)
public class SuccessKilledDaoTest {
	
	@Autowired
	private SuccessKilledDao successKilledDao; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertSuccessKilled() {
		
	}

	@Test
	public void testQueryByIdWithSeckill() {
		int affCount = successKilledDao.insertSuccessKilled(1000L, 18775242354L);
		if(affCount == 1){
			System.out.println("seckill successed!!");
		}
	}

}


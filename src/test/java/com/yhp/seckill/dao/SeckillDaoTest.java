package com.yhp.seckill.dao;

import com.yhp.seckill.ItemSeckillApplication;
import com.yhp.seckill.vo.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by ASUS on 2017/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ItemSeckillApplication.class)
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void querySeckillByID() throws Exception {
    }

    @Test
    public void queryAllSeckill() throws Exception {
       List<Seckill> list =  seckillDao.queryAllSeckill();
        for (Seckill seckill : list) {
            System.out.println(seckill);
        }

    }

}
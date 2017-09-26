package com.yhp.seckill.bo;

import java.util.List;

import com.yhp.seckill.exception.SeckillException;
import com.yhp.seckill.vo.Exposer;
import com.yhp.seckill.vo.Seckill;
import com.yhp.seckill.vo.SeckillExecution;

/**
 * @desc 秒杀业务层
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日下午4:47:39
 */
public interface SeckillBo {
	  /**
     * 查询全部的秒杀记录
     * @return
     */
    List<Seckill> querySeckillList();

    /**
     *查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /**
     * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作，有可能失败，有可能成功，所以要抛出我们允许的异常
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)throws SeckillException;


}


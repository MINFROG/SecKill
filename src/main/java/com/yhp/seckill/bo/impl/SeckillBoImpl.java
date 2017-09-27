package com.yhp.seckill.bo.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhp.seckill.bo.SeckillBo;
import com.yhp.seckill.dao.SeckillDao;
import com.yhp.seckill.dao.SuccessKilledDao;
import com.yhp.seckill.enums.SeckillStatEnum;
import com.yhp.seckill.exception.RepeatKillException;
import com.yhp.seckill.exception.SeckillCloseException;
import com.yhp.seckill.exception.SeckillException;
import com.yhp.seckill.utils.MD5Utils;
import com.yhp.seckill.vo.Exposer;
import com.yhp.seckill.vo.Seckill;
import com.yhp.seckill.vo.SeckillExecution;
import com.yhp.seckill.vo.SuccessKilled;

/**
 * @desc 秒杀业务层接口实现
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日下午4:52:53
 */
@Service
public class SeckillBoImpl implements SeckillBo {
	
	private Logger log = LoggerFactory.getLogger(SeckillBoImpl.class);
	
	private static  final String SALT = "edOM8ed!jS223"; //加盐处理
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;

	@Override
	public List<Seckill> querySeckillList() {
		return seckillDao.queryAllSeckill();
	}

	@Override
	public Seckill queryById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		// 说明查不到这个秒杀产品的记录
		if (seckill == null) return new Exposer(false, seckillId);
		// 若是秒杀未开启
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (startTime.getTime() > nowTime.getTime()|| endTime.getTime() < nowTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),startTime.getTime(), endTime.getTime());
		}
		// 秒杀开启，返回秒杀商品的id、用给接口加密的md5
		String md5 = MD5Utils.getMD5(SALT+seckillId);
		return new Exposer(true, md5, seckillId);
	}

	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException {
		if (md5 == null || !md5.equals(MD5Utils.getMD5(SALT + seckillId))) {
			throw new SeckillException("seckill data rewrite");// 秒杀数据被重写了
		}
		// 执行秒杀逻辑:减库存+增加购买明细
		Date nowTime = new Date();
		try {
			// 减库存
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if (updateCount <= 0) {
				// 没有更新库存记录，说明秒杀结束
				throw new SeckillCloseException("seckill is closed");
			} else {
				// 否则更新了库存，秒杀成功,增加明细
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				// 看是否该明细被重复插入，即用户是否重复秒杀
				if (insertCount <= 0) {
					 throw new RepeatKillException("seckill repeated");
				} else {
					// 秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息
					SuccessKilled successKilled = successKilledDao
							.queryByIdWithSeckill(seckillId, userPhone);
					 return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,successKilled);
				}
			}
		} catch (Exception ex) {
			log.error("秒杀失败！seckillId = {} ,userPhone = {} ,md5 = {}",new Object[] { seckillId, userPhone, md5, ex });
			// 所以编译期异常转化为运行期异常
			 throw new SeckillException("seckill inner error :"+ex.getMessage());
		}

	}

}


package com.yhp.seckill.dao;



import com.yhp.seckill.vo.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @desc TODO添加描述
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月25日下午3:34:56
 */
@Mapper
public interface SeckillDao {
	
	public Seckill querySeckillByID(Long seckillId);
	
	public List<Seckill> queryAllSeckill();

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return 如果影响行数>1，表示更新库存的记录行数
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * 根据id查询秒杀的商品信息
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);

	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

}


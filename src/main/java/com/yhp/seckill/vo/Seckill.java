package com.yhp.seckill.vo;

import java.sql.Timestamp;

/**
 * @desc 秒杀实体
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月25日下午3:35:23
 */
public class Seckill {
	
	private Long seckillId;
	private String name;
	private Integer number;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;

	public Long getSeckillId() {
		return seckillId;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Seckill{" +
				"seckillId=" + seckillId +
				", name='" + name + '\'' +
				", number=" + number +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", createTime=" + createTime +
				'}';
	}
}


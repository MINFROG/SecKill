package com.yhp.seckill.vo;
/**
 * @desc TODO添加描述
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日下午4:50:37
 */
public class SeckillExecution {

	 	private long seckillId;

	    //秒杀执行结果的状态
	    private int state;

	    //状态的明文标识
	    private String stateInfo;

	    //当秒杀成功时，需要传递秒杀成功的对象回去
	    private SuccessKilled successKilled;

	    //秒杀成功返回所有信息
	    public SeckillExecution(long seckillId, int state, String stateInfo, SuccessKilled successKilled) {
	        this.seckillId = seckillId;
	        this.state = state;
	        this.stateInfo = stateInfo;
	        this.successKilled = successKilled;
	    }

	    //秒杀失败
	    public SeckillExecution(long seckillId, int state, String stateInfo) {
	        this.seckillId = seckillId;
	        this.state = state;
	        this.stateInfo = stateInfo;
	    }

	    public long getSeckillId() {
	        return seckillId;
	    }

	    public void setSeckillId(long seckillId) {
	        this.seckillId = seckillId;
	    }

	    public int getState() {
	        return state;
	    }

	    public void setState(int state) {
	        this.state = state;
	    }

	    public String getStateInfo() {
	        return stateInfo;
	    }

	    public void setStateInfo(String stateInfo) {
	        this.stateInfo = stateInfo;
	    }

	    public SuccessKilled getSuccessKilled() {
	        return successKilled;
	    }

	    public void setSuccessKilled(SuccessKilled successKilled) {
	        this.successKilled = successKilled;
	    }
	    
}


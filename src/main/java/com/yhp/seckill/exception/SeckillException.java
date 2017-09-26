package com.yhp.seckill.exception;

import java.io.Serializable;

/**
 * @desc 自定义秒杀异常类
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日下午3:54:48
 */
public class SeckillException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8650521353706640763L;
	
	private Integer errCode;
	
	public Integer getErrCode() {
		return errCode;
	}
	
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	
	public SeckillException(String errMsg){
		super(errMsg);
	}
	
	public SeckillException(Integer errcode , String errMsg){
		super(errMsg);
		this.errCode = errcode;
	}

	public SeckillException(String errMsg,Throwable e){
		super(errMsg, e);
		this.errCode = errCode;
	}

}


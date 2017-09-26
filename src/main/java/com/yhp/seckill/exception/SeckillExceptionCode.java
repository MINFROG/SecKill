package com.yhp.seckill.exception;
/**
 * @desc 异常状态码
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月26日下午4:13:38
 */
public enum SeckillExceptionCode {
	
	
	SECKILL_CLOSE_EXCEPTION(0001,"秒杀关闭异常"),
	SECKILL_REPEAT_EXCEPTION(0002,"重复秒杀异常"),
	SECKILL_UNKNOW_EXCEPTION(9999,"未知异常");
	

	private Integer errCode;
	private String errMsg;
	
	
	SeckillExceptionCode(Integer errCode,String errMsg){
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public Integer getErrCode() {
		return errCode;
	}
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}


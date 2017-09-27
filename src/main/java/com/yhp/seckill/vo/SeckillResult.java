package com.yhp.seckill.vo;
/**
 * @desc TODO添加描述
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月27日下午2:03:36
 */
public class SeckillResult<T> {
	private boolean success;
	private T data;
	private String error;

	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}


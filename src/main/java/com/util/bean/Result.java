package com.util.bean;

public class Result<T> {

	private T data;
	private boolean flag;
	private String message;
	private Integer count;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}

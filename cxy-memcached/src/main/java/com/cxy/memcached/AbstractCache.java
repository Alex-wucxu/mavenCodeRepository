package com.cxy.memcached;

public abstract class AbstractCache implements ICache {

	// 模块名称
	protected String basename;

	// 超时时长
	protected int timeout;

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
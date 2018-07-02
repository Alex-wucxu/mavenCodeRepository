package com.cxy.memcached.manage.impl;

import java.util.Date;

import com.cxy.memcached.AbstractCache;
import com.cxy.memcached.manage.MemcachedContainer;
import com.cxy.memcached.manage.MemcachedManager;
import com.whalin.MemCached.MemCachedClient;

public class MemcachedCacheImpl extends AbstractCache {

	private MemCachedClient mcc = MemcachedManager.getClient();

	public boolean add(Object key, Object value) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		int exp = this.getTimeout();
		if (exp == 0) {
			return mcc.add(this.getBasename() + key, value);
		} else {
			// Date date = new Date(System.currentTimeMillis()+exp * 1000);
			Date date = new Date(exp * 1000);
			return mcc.add(this.getBasename() + key, value, date);
		}
	}

	public Object get(Object key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return null;
		}
		return this.get(key, false);
	}

	public Object get(Object key, boolean refresh) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return null;
		}
		Object o = mcc.get(this.getBasename() + key);
		if (refresh && o != null) {
			this.set(key, o);
		}
		return o;
	}

	public boolean remove(Object key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.delete(this.getBasename() + key);
	}

	public boolean set(Object key, Object value) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		int exp = this.getTimeout();
		if (exp == 0) {
			return mcc.set(this.getBasename() + key, value);
		} else {
			// Date date = new Date(System.currentTimeMillis()+exp * 1000);
			Date date = new Date(exp * 1000);
			return mcc.set(this.getBasename() + key, value, date);
		}
	}

	public boolean isAlive(String key) {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.keyExists(this.getBasename() + key);
	}

	public boolean flush() {
		if (!MemcachedContainer.getInstance().isMemcachedSwitch()) {
			return false;
		}
		return mcc.flushAll();
	}

}
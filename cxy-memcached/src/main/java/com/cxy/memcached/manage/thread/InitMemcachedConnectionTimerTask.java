package com.cxy.memcached.manage.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;

import com.cxy.memcached.manage.MemcachedContainer;
import com.cxy.memcached.manage.MemcachedManager;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class InitMemcachedConnectionTimerTask extends TimerTask {

	private final static String CHECK_POOL_NAME = "checkPool";

	public void run() {
		// LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask start.... " +
		// new Date());
		Map<String, Integer> allServerMap = MemcachedContainer.getInstance().getAllServerMap();
		
		// 校验缓存服务是否存活
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : allServerMap.entrySet()) {
			String server = entry.getKey();
			Integer weight = entry.getValue();
			
			//校验
			if (checkNodeStatus(server, weight)) {
				map.put(server, weight);
			}
		}
		
		//判断是否和之前的所有缓存服务数量一致，如果不是，则重新启动缓存服务
		if (map.size() > 0 && map.size() != MemcachedContainer.getInstance().getServerMap().size()) {
			Set<Entry<String, Integer>> entrys = map.entrySet();
			Iterator<Entry<String, Integer>> iterator = entrys.iterator();
			String[] newServers = new String[map.size()];
			Integer[] newWeights = new Integer[map.size()];
			int j = 0;
			
			//重新设置服务权重
			while (iterator.hasNext()) {
				Entry<String, Integer> entry = iterator.next();
				newServers[j] = entry.getKey();
				newWeights[j++] = entry.getValue();
			}
			MemcachedContainer.getInstance().setServerMap(map);
			MemcachedManager.initMemcached();
			// LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask have change!!!!");
			// LogUtil.infoSystemLog("IP:");
			// for (String newServer : newServers) {
			// LogUtil.infoSystemLog(newServer);
			// }
		}
		// LogUtil.infoSystemLog("InitMemcachedConnectionTimerTask over.... " +
		// new Date());
	}

	private boolean checkNodeStatus(String server, int weight) {
		SockIOPool pool = SockIOPool.getInstance(CHECK_POOL_NAME);
		pool.setServers(new String[] { server });
		pool.setWeights(new Integer[] { weight });
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(4000);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setAliveCheck(false);
		pool.setFailover(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		pool.initialize();
		MemCachedClient memCachedClient = new MemCachedClient(CHECK_POOL_NAME);
		memCachedClient.set("isConnected", true, new Date(3000));
		Object o = memCachedClient.get("isConnected");
		if (o != null && (Boolean) o) {
			return true;
		}
		return false;
	}

}

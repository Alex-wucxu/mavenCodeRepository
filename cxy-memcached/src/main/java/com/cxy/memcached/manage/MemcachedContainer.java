package com.cxy.memcached.manage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.cxy.memcached.util.ResourceUtil;


public class MemcachedContainer {

	// 缓存开关
	private boolean memcachedSwitch = false;

	// 检查缓存服务是否失效开关
	private boolean memcachedAliveTaskSwitch = false;

	//所有服务区群
	private Map<String, Integer> serverMap;
	private Map<String, Integer> allServerMap;

	private static MemcachedContainer memCachedContainer = null;

	public static MemcachedContainer getInstance() {
		return memCachedContainer;
	}

	public boolean isMemcachedSwitch() {
		return memcachedSwitch;
	}

	private void setMemcachedSwitch(boolean memcachedSwitch) {
		this.memcachedSwitch = memcachedSwitch;
	}

	public boolean isMemcachedAliveTaskSwitch() {
		return memcachedAliveTaskSwitch;
	}

	private void setMemcachedAliveTaskSwitch(boolean memcachedAliveTaskSwitch) {
		this.memcachedAliveTaskSwitch = memcachedAliveTaskSwitch;
	}

	public Map<String, Integer> getServerMap() {
		return serverMap;
	}

	public void setServerMap(Map<String, Integer> serverMap) {
		this.serverMap = serverMap;
	}

	public Map<String, Integer> getAllServerMap() {
		return allServerMap;
	}

	public void setAllServerMap(Map<String, Integer> allServerMap) {
		this.allServerMap = allServerMap;
	}

	static {
		memCachedContainer = new MemcachedContainer();
	}

	private MemcachedContainer() {
		String cacheXmlPath = ResourceUtil.getResourceConfPath() + "/cache.xml";
		initMemcachedContainer(cacheXmlPath);
	}

	//初始化基本信息
	private void initMemcachedContainer(String cacheXmlPath) {
		InputStream is = null;
		try {
			//读取配置文件
			is = new FileInputStream(cacheXmlPath);
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(is);
			
			//判断缓存是否打开
			Node memcachedSwitch = doc.selectSingleNode("//memcached-switch");
			if (!"true".equals(memcachedSwitch.getText())) {
				return;
			}
			this.setMemcachedSwitch(true);
			
			//判断是否开启检查
			Node aliveTaskSwitch = doc.selectSingleNode("//memcached-alive-task-switch");
			if ("true".equals(aliveTaskSwitch.getText())) {
				this.setMemcachedAliveTaskSwitch(true);
			}
			
			//获得服务区群
			Node serversNode = doc.selectSingleNode("//memcached-servers");
			
			@SuppressWarnings("unchecked")
			List<Element> serverList = (List<Element>) serversNode.selectNodes("//memcached-server");
			serverMap = new HashMap<String, Integer>();
			allServerMap = new HashMap<String, Integer>();
			for (Iterator<Element> iterator = serverList.iterator(); iterator.hasNext();) {
				Element serverElement = iterator.next();
				String ip = serverElement.elementText("ip");
				int port = Integer.parseInt(serverElement.elementText("port"));
				int weight = Integer.parseInt(serverElement.elementText("weight"));
				String server = ip + ":" + port;
				serverMap.put(server, weight);
				allServerMap.put(server, weight);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
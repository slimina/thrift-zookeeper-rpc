package cn.slimsmart.thrift.rpc.zookeeper;

import java.io.UnsupportedEncodingException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.slimsmart.thrift.rpc.ThriftException;

/**
 *  注册服务列表到Zookeeper
 */
public class ThriftServerAddressRegisterZookeeper implements ThriftServerAddressRegister{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CuratorFramework zkClient;
	
	public ThriftServerAddressRegisterZookeeper(){}
	
	public ThriftServerAddressRegisterZookeeper(CuratorFramework zkClient){
		this.zkClient = zkClient;
	}

	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}

	@Override
	public void register(String service, String version, String address) {
		if(zkClient.getState() == CuratorFrameworkState.LATENT){
			zkClient.start();
		}
		if(StringUtils.isEmpty(version)){
			version="1.0.0";
		}
		//临时节点
		try {
			zkClient.create()
				.creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath("/"+service+"/"+version+"/"+address);
		} catch (UnsupportedEncodingException e) {
			logger.error("register service address to zookeeper exception:{}",e);
			throw new ThriftException("register service address to zookeeper exception: address UnsupportedEncodingException", e);
		} catch (Exception e) {
			logger.error("register service address to zookeeper exception:{}",e);
			throw new ThriftException("register service address to zookeeper exception:{}", e);
		}
	}
	
	public void close(){
		zkClient.close();
	}
}

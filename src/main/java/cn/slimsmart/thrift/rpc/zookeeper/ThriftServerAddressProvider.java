package cn.slimsmart.thrift.rpc.zookeeper;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * thrift server-service地址提供者,以便构建客户端连接池
 */
public interface ThriftServerAddressProvider {
	
	//获取服务名称
	String getService();

	/**
	 * 获取所有服务端地址
	 * @return
	 */
    List<InetSocketAddress> findServerAddressList();

    /**
     * 选取一个合适的address,可以随机获取等'
     * 内部可以使用合适的算法.
     * @return
     */
    InetSocketAddress selector();

    void close();
}

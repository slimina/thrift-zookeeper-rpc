package cn.slimsmart.thrift.rpc.demo;

import org.apache.thrift.TException;

//实现类
public class EchoSerivceImpl implements EchoSerivce.Iface {

	@Override
	public String echo(String msg) throws TException {
		return "server :"+msg;
	}
}

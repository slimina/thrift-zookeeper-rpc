package cn.slimsmart.thrift.rpc.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//服务端启动
public class Server {

	public static void main(String[] args) {
		try {
			new ClassPathXmlApplicationContext("classpath:spring-context-thrift-server.xml");
			Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

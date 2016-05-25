package io.github.kuyer.rpc.demo;

import io.github.kuyer.rpc.demo.api.CustomerApi;
import io.github.kuyer.rpc.demo.api.HelloRpcApi;
import io.github.kuyer.rpc.demo.api.model.CustomerModel;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rpc-netty-client.xml"})
public class NettyChannelClientTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HelloRpcApi helloRpcApi;
	@Autowired
	private CustomerApi customerApi;
	
	@Test
	public void testNettyClient() {
		for(int i=0; i<100; i++) {
			final int index = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("request index: "+index);
					test();
				}}).start();
		}
	}
	
	private void test() {
		helloRpcApi.sayHello("kuyer");
		//logger.info("4+8={}", helloRpcApi.plus(4, 8));
		customerApi.getCustomerById(100);
		customerApi.getCustomerList(new CustomerModel(101, "kuyer-rpc-101", new Date()));
	}

}

package io.github.kuyer.rpc.demo;

import io.github.kuyer.rpc.demo.api.HelloRpcApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rpc-demo-client.xml"})
public class HelloRpcApiTest {
	
	private Logger logger = LoggerFactory.getLogger(HelloRpcApiTest.class);
	
	@Autowired
	private HelloRpcApi helloRpcApi;
	
	@Test
	public void testSayHello() {
		logger.info(helloRpcApi.sayHello("kuyer"));
	}
	
	@Test
	public void testPlus() {
		logger.info("4+8={}", helloRpcApi.plus(4, 8));
	}

}

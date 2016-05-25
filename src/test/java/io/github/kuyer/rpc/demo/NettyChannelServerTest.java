package io.github.kuyer.rpc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-rpc-netty.xml"})
public class NettyChannelServerTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testNettyServer() {
		logger.info("test netty server.");
	}

}

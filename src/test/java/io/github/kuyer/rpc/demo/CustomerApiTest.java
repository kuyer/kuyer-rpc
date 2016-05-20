package io.github.kuyer.rpc.demo;

import io.github.kuyer.rpc.demo.api.CustomerApi;
import io.github.kuyer.rpc.demo.api.model.CustomerModel;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rpc-demo-client.xml"})
public class CustomerApiTest {
	
	private Logger logger = LoggerFactory.getLogger(CustomerApiTest.class);
	
	@Autowired
	private CustomerApi customerApi;
	
	@Test
	public void testGetCustomerById() {
		logger.info("CustomerApi.getCustomerById(100): {}", customerApi.getCustomerById(100).toString());
	}
	
	@Test
	public void testGetCustomerList() {
		List<CustomerModel> list = customerApi.getCustomerList(new CustomerModel(101, "kuyer-rpc-101", new Date()));
		for(CustomerModel model : list) {
			logger.info("{}", model.toString());
		}
	}

}

package io.github.kuyer.rpc.demo.service;

import org.springframework.stereotype.Service;

import io.github.kuyer.rpc.demo.api.HelloRpcApi;

/**
 * HelloRpcApi接口实现
 * @author Rory.Zhang
 */
@Service
public class HelloRpcService implements HelloRpcApi {

	@Override
	public String sayHello(String name) {
		if(null == name) {
			return "hello, everybody!";
		}
		return String.format("hello, %s!", name);
	}

	@Override
	public int plus(int a, int b) {
		return a+b;
	}

}

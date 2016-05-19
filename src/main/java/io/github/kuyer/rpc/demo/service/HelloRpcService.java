package io.github.kuyer.rpc.demo.service;

import io.github.kuyer.rpc.demo.api.HelloRpcApi;

/**
 * HelloRpcApi接口实现
 * @author Rory.Zhang
 */
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

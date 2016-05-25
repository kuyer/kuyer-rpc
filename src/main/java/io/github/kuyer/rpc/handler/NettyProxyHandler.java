package io.github.kuyer.rpc.handler;

import io.github.kuyer.rpc.channel.NettyChannelClient;
import io.github.kuyer.rpc.codec.RpcRequest;
import io.github.kuyer.rpc.codec.RpcResponse;
import io.github.kuyer.rpc.common.MessageUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NettyProxyHandler implements InvocationHandler {
	
	private NettyChannelClient client;
	private Class<?> serviceApi;

	public NettyProxyHandler(NettyChannelClient client, Class<?> serviceApi) {
		this.client = client;
		this.serviceApi = serviceApi;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		RpcRequest request = new RpcRequest();
		request.setMessageId(MessageUtil.getMessageId());
		request.setServiceApi(serviceApi);
		request.setMethodName(method.getName());
		request.setParams(params);
		RpcResponse response = client.request(request);
		if(null != response) {
			if(null != response.getResult()) {
				return response.getResult();
			} else if(null != response.getException()) {
				throw response.getException();
			}
		}
		throw new NullPointerException();
	}

}

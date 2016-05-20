package io.github.kuyer.rpc.handler;

import io.github.kuyer.rpc.codec.BaseCodec;
import io.github.kuyer.rpc.codec.RpcRequest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http之Handler
 * @author Rory.Zhang
 */
public class HttpHandler implements InvocationHandler {
	
	private String serviceUrl;
	
	public HttpHandler(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParams(args);
		return request(rpcRequest);
	}

	private Object request(RpcRequest request) throws Exception {
		byte[] requestBytes = BaseCodec.encode(request);
		
		URL url = new URL(this.serviceUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(5000);
		conn.setDoOutput(true);
		conn.getOutputStream().write(requestBytes);
		
		InputStream is = conn.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=is.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		byte[] responseBytes = bos.toByteArray();
		bos.close();
		is.close();
		
		return BaseCodec.decode(responseBytes);
	}

}

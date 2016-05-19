package io.github.kuyer.rpc.channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.HttpRequestHandler;

import io.github.kuyer.rpc.codec.BaseCodec;
import io.github.kuyer.rpc.codec.RpcRequest;

/**
 * Http通道Exporter
 * @author Rory.Zhang
 */
public class HttpChannelExporter extends RemoteExporter implements HttpRequestHandler {
	
	private Logger logger = LoggerFactory.getLogger(HttpChannelExporter.class);

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("handler request ...");
		try {
			InputStream is = request.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=is.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			byte[] requestBytes = bos.toByteArray();
			bos.close();
			is.close();
			
			RpcRequest rpcRequest = (RpcRequest) BaseCodec.decode(requestBytes);
			String methodName = rpcRequest.getMethodName();
			Object[] params = rpcRequest.getParams();
			
			Class<?>[] clazzes = new Class<?>[params.length];
			for(int i=0; i<params.length; i++) {
				Object param = params[i];
				clazzes[i] = param.getClass();
			}
			
			Object service = getService();
			Method method = service.getClass().getDeclaredMethod(methodName, clazzes);
			Object obj = method.invoke(service, params);
			
			byte[] responseBytes = BaseCodec.encode(obj);
			OutputStream os = response.getOutputStream();
			os.write(responseBytes);
			os.flush();
		} catch (Exception e) {
			logger.error("handle request error. ", e);
			throw new IOException();
		}
	}

}

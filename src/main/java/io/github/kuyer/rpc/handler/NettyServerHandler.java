package io.github.kuyer.rpc.handler;

import io.github.kuyer.rpc.codec.RpcRequest;
import io.github.kuyer.rpc.codec.RpcResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Netty之Handler
 * @author Rory.Zhang
 */
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> implements ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext applicationContext;
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, RpcRequest request)
			throws Exception {
		logger.info("netty rpc server execute message. messageId={}, request={}", request.getMessageId(), request);
		try {
			Class<?>[] clazzes = new Class<?>[request.getParams().length];
			for(int i=0; i<request.getParams().length; i++) {
				Object param = request.getParams()[i];
				clazzes[i] = param.getClass();
			}
			
			Object instanceApi = applicationContext.getBean(request.getServiceApi());
			Method method = instanceApi.getClass().getMethod(request.getMethodName(), clazzes);
			Object result = method.invoke(instanceApi, request.getParams());
			
			RpcResponse response = new RpcResponse();
			response.setMessageId(request.getMessageId());
			response.setResult(result);
			ctx.writeAndFlush(response);
		} catch (Exception e) {
			throw new Exception(request.getMessageId());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		RpcResponse response = new RpcResponse();
		response.setMessageId(cause.getMessage());
		response.setException(cause);
		ctx.writeAndFlush(response);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}

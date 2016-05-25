package io.github.kuyer.rpc.handler;

import io.github.kuyer.rpc.codec.RpcResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, RpcResponse> responseMap = new ConcurrentHashMap<String, RpcResponse>();
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, RpcResponse response)
			throws Exception {
		logger.info("netty rpc client receive message. messageId={}, response={}", response.getMessageId(), response);
		responseMap.put(response.getMessageId(), response);
	}

	public RpcResponse getResponse(String messageId) {
		RpcResponse response = null;
		while(null == response) {
			response = responseMap.get(messageId);
		}
		responseMap.remove(messageId);
		return response;
	}

}

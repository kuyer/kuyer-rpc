package io.github.kuyer.rpc.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Netty之Handler
 * @author Rory.Zhang
 */
public class NettyHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object request)
			throws Exception {
		Object response = new Object();
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}

package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.codec.RpcResponse;
import io.github.kuyer.rpc.handler.NettyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyChannelClientInitializer extends ChannelInitializer<SocketChannel> {

	private NettyClientHandler handler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder());
		ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
		ch.pipeline().addLast(handler);
	}
	
	public RpcResponse getResponse(String messageId) {
		return handler.getResponse(messageId);
	}

	public void setHandler(NettyClientHandler handler) {
		this.handler = handler;
	}

}

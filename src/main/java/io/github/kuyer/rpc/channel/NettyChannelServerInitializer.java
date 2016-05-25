package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.handler.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyChannelServerInitializer extends ChannelInitializer<SocketChannel> {
	
	private NettyServerHandler handler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder());
		ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
		ch.pipeline().addLast(handler);
	}

	public void setHandler(NettyServerHandler handler) {
		this.handler = handler;
	}

}

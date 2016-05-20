package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.codec.NettyRpcDecode;
import io.github.kuyer.rpc.codec.NettyRpcEncode;
import io.github.kuyer.rpc.handler.NettyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Netty通道服务
 * @author Rory.Zhang
 */
public class NettyChannelServer implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(NettyChannelServer.class);
	
	/** 服务端口 **/
	private Integer port;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast(new NettyRpcDecode()).addLast(new NettyRpcEncode()).addLast(new NettyHandler());
				}}).option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = bootstrap.bind(port).sync();
			logger.info("kuyer rpc server start on port: {}", this.port);
			future.channel().closeFuture().sync();
		} finally {
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}

package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.common.RpcConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Netty服务通道服务
 * @author Rory.Zhang
 */
public class NettyChannelServer implements InitializingBean, DisposableBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 服务端口 **/
	private Integer port;
	/** boss线程数 **/
	private Integer bossThreads;
	/** work线程数 **/
	private Integer workThreads;
	/** Backlog Size **/
	private Integer backlogSize;
	private NettyChannelServerInitializer channelInitializer;
	
	/** Netty通道 **/
	private Channel channel;
	EventLoopGroup bossGroup;
	EventLoopGroup workGroup;
	
	
	/** 启动Netty服务 **/
	public void start() throws Exception {
		if(null == port) {
			port = RpcConstant.NETTY_PORT;
		}
		if(port < 0) {
			port = RpcConstant.NETTY_PORT;
		}
		if(null == bossThreads) {
			bossThreads = 0;
		}
		if(bossThreads < 0) {
			bossThreads = 0;
		}
		if(null == workThreads) {
			workThreads = 0;
		}
		if(workThreads < 0) {
			workThreads = 0;
		}
		bossGroup = new NioEventLoopGroup(bossThreads);
		workGroup = new NioEventLoopGroup(workThreads);
		
		if(null == backlogSize) {
			backlogSize = RpcConstant.BACKLOG_SIZE;
		}
		if(backlogSize < 0) {
			backlogSize = RpcConstant.BACKLOG_SIZE;
		}
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, backlogSize)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childOption(ChannelOption.TCP_NODELAY, true)
			.childHandler(channelInitializer);
		channel = bootstrap.bind(port).sync().channel();
		logger.info("kuyer rpc server start on port: {}", this.port);
	}
	
	/** 停止Netty服务 **/
	public void stop() throws Exception {
		if(null != channel) {
			channel.closeFuture().sync();
		}
		workGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		workGroup = null;
		bossGroup = null;
		channel = null;
		logger.info("kuyer rpc server is stopped.");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@Override
	public void destroy() throws Exception {
		stop();
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	public void setBossThreads(Integer bossThreads) {
		this.bossThreads = bossThreads;
	}
	public void setWorkThreads(Integer workThreads) {
		this.workThreads = workThreads;
	}
	public void setBacklogSize(Integer backlogSize) {
		this.backlogSize = backlogSize;
	}

	public void setChannelInitializer(NettyChannelServerInitializer channelInitializer) {
		this.channelInitializer = channelInitializer;
	}

}

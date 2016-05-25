package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.codec.RpcRequest;
import io.github.kuyer.rpc.codec.RpcResponse;
import io.github.kuyer.rpc.common.RpcConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Netty客户端通道
 * @author Rory.Zhang
 */
public class NettyChannelClient implements InitializingBean, DisposableBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 服务主机地址 **/
	private String host;
	/** 服务主机端口 **/
	private Integer port;
	/** 线程数 **/
	private Integer workThreads;
	private NettyChannelClientInitializer channelInitializer;
	
	private EventLoopGroup workGroup;
	private Channel channel;
	
	public void connect() throws Exception {
		if(null == port) {
			port = RpcConstant.NETTY_PORT;
		}
		if(port < 0) {
			port = RpcConstant.NETTY_PORT;
		}
		if(null == workThreads) {
			workThreads = 0;
		}
		if(workThreads < 0) {
			workThreads = 0;
		}
		workGroup = new NioEventLoopGroup(workThreads);
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(workGroup).channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(channelInitializer);
		channel = bootstrap.connect(host, port).syncUninterruptibly().channel();
		logger.info("kuyer client connect {}:{} success.", host, port);
	}
	
	public RpcResponse request(RpcRequest request) {
		channel.writeAndFlush(request);
		return channelInitializer.getResponse(request.getMessageId());
	}
	
	public void close() throws Exception {
		if(null != channel) {
			channel.closeFuture().syncUninterruptibly();
		}
		workGroup.shutdownGracefully();
		workGroup = null;
		channel = null;
		logger.info("kuyer client close {}:{}.", host, port);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		connect();
	}
	
	@Override
	public void destroy() throws Exception {
		close();
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setWorkThreads(Integer workThreads) {
		this.workThreads = workThreads;
	}

	public void setChannelInitializer(
			NettyChannelClientInitializer channelInitializer) {
		this.channelInitializer = channelInitializer;
	}

}

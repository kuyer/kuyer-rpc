package io.github.kuyer.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Netty编码器
 * @author Rory.Zhang
 */
public class NettyRpcEncode extends MessageToByteEncoder<Object> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		byte[] bytes = BaseCodec.encode(msg);
		out.writeInt(bytes.length);
		out.writeBytes(bytes);
	}

}

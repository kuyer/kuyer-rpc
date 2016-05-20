package io.github.kuyer.rpc.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Netty解码器
 * @author Rory.Zhang
 */
public class NettyRpcDecode extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if(in.readableBytes() < 0) {
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if(dataLength < 0) {
			ctx.close();
		}
		if(in.readableBytes() < dataLength) {
			in.resetReaderIndex();
		}
		byte[] bytes = new byte[dataLength];
		in.readBytes(bytes);
		Object obj = BaseCodec.decode(bytes);
		out.add(obj);
	}


}

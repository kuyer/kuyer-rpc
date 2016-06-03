package io.github.kuyer.rpc.codec;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * protostuff基于protobuf
 * http://www.protostuff.io/
 * @author Rory.Zhang
 */
public class ProtostuffCodec {
	
	private static Objenesis objenesis = new ObjenesisStd(true);
	
	@SuppressWarnings("unchecked")
	public static <T> byte[] encode(T obj) {
		Class<T> clazz = (Class<T>) obj.getClass();
		return encode(obj, clazz);
	}
	
	public static <T> byte[] encode(T obj, Class<T> clazz) {
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		buffer.clear();
		return bytes;
	}
	
	public static <T> T decode(byte[] bytes, Class<T> clazz) {
		T message = objenesis.newInstance(clazz);
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, message, schema);
		return message;
	}

}

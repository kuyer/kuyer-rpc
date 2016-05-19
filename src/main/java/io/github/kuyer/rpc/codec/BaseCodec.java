package io.github.kuyer.rpc.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Java基础的编码解码器
 * @author Rory.Zhang
 */
public class BaseCodec {
	
	/**
	 * 编码方法
	 * @param obj 要编码的对象
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode(Object obj) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.toByteArray();
	}
	
	/**
	 * 解码方法
	 * @param bytes 要解码的字节数组
	 * @return
	 * @throws Exception
	 */
	public static Object decode(byte[] bytes) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

}

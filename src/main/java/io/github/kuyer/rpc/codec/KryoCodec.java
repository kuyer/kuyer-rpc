package io.github.kuyer.rpc.codec;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoCodec {
	
	public static byte[] encode(Object obj) {
		Kryo kryo = new Kryo();
		byte[] buffer = new byte[10240000];
		Output output = new Output(buffer);
		kryo.writeClassAndObject(output, obj);
		byte[] bytes = output.toBytes();
		output.close();
		return bytes;
	}
	
	public static Object decode(byte[] bytes) {
		Kryo kryo = new Kryo();
		Input input = new Input(bytes);
		Object obj = kryo.readClassAndObject(input);
		input.close();
		return obj;
	}

}

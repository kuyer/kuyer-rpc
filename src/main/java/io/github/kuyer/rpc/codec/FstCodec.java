package io.github.kuyer.rpc.codec;

import org.nustaq.serialization.FSTConfiguration;

public class FstCodec {
	
	private static FSTConfiguration fstConf = FSTConfiguration.createDefaultConfiguration();
	
	public static byte[] encode(Object obj) {
		return fstConf.asByteArray(obj);
	}
	
	public static Object decode(byte[] bytes) {
		return fstConf.asObject(bytes);
	}

}

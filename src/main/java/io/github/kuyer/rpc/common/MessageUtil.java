package io.github.kuyer.rpc.common;

import java.util.concurrent.atomic.AtomicLong;

public class MessageUtil {
	
	private static AtomicLong sequence = new AtomicLong(0);
	
	public static String getMessageId() {
		return String.valueOf(sequence.getAndIncrement());
	}
	
	public static void main(String[] args) {
		for(int i=0; i<100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(MessageUtil.getMessageId());
				}}).start();
		}
	}

}

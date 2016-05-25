package io.github.kuyer.rpc.codec;

import java.io.Serializable;

/**
 * RPC响应
 * @author Rory.Zhang
 */
public class RpcResponse implements Serializable {

	private static final long serialVersionUID = 2078652256342929839L;
	
	/** 消息ID，唯一 **/
	private String messageId;
	/** 响应结果 **/
	private Object result;
	/** 响应异常 **/
	private Throwable exception;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
	@Override
	public String toString() {
		return "RpcResponse [messageId=" + messageId + ", result=" + result
				+ ", exception=" + exception + "]";
	}

}

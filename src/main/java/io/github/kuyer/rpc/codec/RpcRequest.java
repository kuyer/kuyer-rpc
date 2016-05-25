package io.github.kuyer.rpc.codec;

import java.io.Serializable;
import java.util.Arrays;

/**
 * RPC请求
 * @author Rory.Zhang
 */
public class RpcRequest implements Serializable {

	private static final long serialVersionUID = -1324097787272006626L;
	
	/** 消息ID，唯一 **/
	private String messageId;
	/** API接口 **/
	private Class<?> serviceApi;
	/** 方法名 **/
	private String methodName;
	/** 参数 **/
	private Object[] params;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Class<?> getServiceApi() {
		return serviceApi;
	}
	public void setServiceApi(Class<?> serviceApi) {
		this.serviceApi = serviceApi;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	
	@Override
	public String toString() {
		return "RpcRequest [messageId=" + messageId + ", serviceApi="
				+ serviceApi + ", methodName=" + methodName + ", params="
				+ Arrays.toString(params) + "]";
	}

}

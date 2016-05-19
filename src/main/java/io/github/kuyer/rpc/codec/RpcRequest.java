package io.github.kuyer.rpc.codec;

import java.io.Serializable;

/**
 * RPC请求
 * @author Rory.Zhang
 */
public class RpcRequest implements Serializable {

	private static final long serialVersionUID = -1324097787272006626L;
	
	/** 方法名 **/
	private String methodName;
	/** 参数 **/
	private Object[] params;
	
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

}

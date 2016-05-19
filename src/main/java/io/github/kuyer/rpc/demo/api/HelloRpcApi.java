package io.github.kuyer.rpc.demo.api;

/**
 * HelloRpcApi接口
 * @author Rory.Zhang
 */
public interface HelloRpcApi {
	
	/**
	 * 和name打个照顾
	 * @param name
	 * @return
	 */
	public String sayHello(String name);
	
	/**
	 * 计算a+b的结果
	 * @param a
	 * @param b
	 * @return
	 */
	public int plus(int a, int b);

}

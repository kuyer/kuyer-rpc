package io.github.kuyer.rpc.demo.api;

import io.github.kuyer.rpc.demo.api.model.CustomerModel;

import java.util.List;

/**
 * 客户API接口
 * @author Rory.Zhang
 */
public interface CustomerApi {
	
	/**
	 * 根据客户ID获取客户信息
	 * @param id
	 * @return
	 */
	public CustomerModel getCustomerById(Integer id);
	
	/**
	 * 根据客户部分条件获取客户信息列表
	 * @param params
	 * @return
	 */
	public List<CustomerModel> getCustomerList(CustomerModel params);

}

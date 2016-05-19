package io.github.kuyer.rpc.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.kuyer.rpc.demo.api.CustomerApi;
import io.github.kuyer.rpc.demo.api.model.CustomerModel;

/**
 * 客户API接口实现
 * @author Rory.Zhang
 */
@Service
public class CustomerService implements CustomerApi {

	@Override
	public CustomerModel getCustomerById(Integer id) {
		if(null == id) {
			return null;
		}
		return new CustomerModel(id, "kuyer-rpc-"+id, new Date());
	}

	@Override
	public List<CustomerModel> getCustomerList(CustomerModel params) {
		if(null == params) {
			return new ArrayList<CustomerModel>();
		}
		String namePrefix = "kuyer-rpc-";
		Date date = new Date();
		List<CustomerModel> list = new ArrayList<CustomerModel>();
		for(int i=100; i<120; i++) {
			list.add(new CustomerModel(i, namePrefix+i, date));
		}
		return list;
	}

}

package io.github.kuyer.rpc.codec;

import java.io.Serializable;
import java.util.List;

import io.github.kuyer.rpc.demo.api.model.CustomerModel;

public class TestModel implements Serializable {

	private static final long serialVersionUID = 5036082519930204416L;
	
	private List<CustomerModel> customers;

	public List<CustomerModel> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerModel> customers) {
		this.customers = customers;
	}

}

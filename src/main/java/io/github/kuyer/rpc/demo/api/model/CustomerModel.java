package io.github.kuyer.rpc.demo.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息数据模型
 * @author Rory.Zhang
 */
public class CustomerModel implements Serializable {

	private static final long serialVersionUID = -4055806328657760261L;
	/** 客户ID **/
	private Integer id;
	/** 客户名称 **/
	private String name;
	/** 客户生日 **/
	private Date birthday;
	
	public CustomerModel() {}
	
	public CustomerModel(Integer id, String name, Date birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "CustomerModel [id=" + id + ", name=" + name + ", birthday="
				+ birthday + "]";
	}

}

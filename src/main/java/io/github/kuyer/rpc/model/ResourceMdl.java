package io.github.kuyer.rpc.model;

import java.io.Serializable;

/**
 * 资源信息（name & ip & port 唯一）
 * @author Rory.Zhang
 */
public class ResourceMdl implements Serializable {

	private static final long serialVersionUID = 1114263650530357052L;
	
	/** 服务名称 **/
	private String name;
	/** 主机IP **/
	private String ip;
	/** 占用端口 **/
	private Integer port;
	/** 资源别名 **/
	private String alias;
	/** 权重 (默认100) **/
	private Integer weight;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}

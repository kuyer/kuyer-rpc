package io.github.kuyer.rpc.channel;

import java.lang.reflect.Proxy;

import io.github.kuyer.rpc.handler.HttpHandler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;

/**
 * Http通道代理
 * @author Rory.Zhang
 */
public class HttpChannelProxy extends UrlBasedRemoteAccessor implements FactoryBean<Object>, MethodInterceptor {
	
	private Object proxyApi;
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		this.proxyApi = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
	}
	
	@Override
	public Object getObject() throws Exception {
		return this.proxyApi;
	}

	@Override
	public Class<?> getObjectType() {
		return getServiceInterface();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object proxyService = createProxyService();
		return invocation.getMethod().invoke(proxyService, invocation.getArguments());
	}

	private Object createProxyService() throws Exception {
		HttpHandler handler = new HttpHandler(getServiceUrl());
		return Proxy.newProxyInstance(getBeanClassLoader(), new Class[] { getServiceInterface() }, handler);
	}

}

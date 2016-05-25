package io.github.kuyer.rpc.channel;

import io.github.kuyer.rpc.handler.NettyProxyHandler;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

public class NettyChannelProxy implements InitializingBean, FactoryBean<Object>, ApplicationContextAware, MethodInterceptor {
	
	private ApplicationContext applicationContext;
	private NettyChannelClient client;
	private Class<?> serviceApi;
	private Object proxyApi;
	
	private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
	
	@Override
	public void afterPropertiesSet() {
		this.client = applicationContext.getBean(NettyChannelClient.class);
		this.proxyApi = new ProxyFactory(serviceApi, this).getProxy(classLoader);
	}
	
	@Override
	public Object getObject() throws Exception {
		return proxyApi;
	}

	@Override
	public Class<?> getObjectType() {
		return serviceApi;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		NettyProxyHandler handler = new NettyProxyHandler(client, serviceApi);
		Object proxyService = Proxy.newProxyInstance(classLoader, new Class[] { serviceApi }, handler);
		return invocation.getMethod().invoke(proxyService, invocation.getArguments());
	}

	public void setServiceApi(Class<?> serviceApi) {
		this.serviceApi = serviceApi;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}

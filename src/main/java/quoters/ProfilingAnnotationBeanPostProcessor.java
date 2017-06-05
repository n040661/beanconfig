package quoters;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
	private Map<String,Class<?>>map=new HashMap<>();
	private ProfilingController controller=new ProfilingController();
	
	public ProfilingAnnotationBeanPostProcessor() throws Exception {
		MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		platformMBeanServer.registerMBean(controller, new ObjectName("profiling","name","controller"));

	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = map.get(beanName);
		if(beanClass!=null){
				return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if(controller.isEnable()){
							System.out.println("profiling...");
							long before=System.nanoTime();
							Object invoke = method.invoke(bean, args);
							long after=System.nanoTime();
							System.out.println("profiling time: "+(after-before));
							return invoke;
						}else{
							return method.invoke(bean, args);
						}
					};
						
				});
		}
		return bean;
		
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)  throws BeansException {
		Class<?> beanClass = bean.getClass();
		if(beanClass.isAnnotationPresent(Profiling.class)){
			map.put(beanName, beanClass);
		}
		return bean;
		
	}

}

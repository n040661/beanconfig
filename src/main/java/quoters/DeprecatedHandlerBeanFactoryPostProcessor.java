package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//BeanFactoryPostProcessor works before BeanPostProcessor
public class DeprecatedHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			String beanClassName = beanDefinition.getBeanClassName();
			try {
				Class<?> className = Class.forName(beanClassName);
				DeprecatedClass annotation = className.getAnnotation(DeprecatedClass.class);
				if(annotation!=null){
					beanDefinition.setBeanClassName(annotation.newImpl().getName());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

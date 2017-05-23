package quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertiesFileApplicationContext extends GenericApplicationContext {
	public PropertiesFileApplicationContext(String fileName){
		PropertiesBeanDefinitionReader definitionReader = new PropertiesBeanDefinitionReader(this);
		int loadBeanDefinitions = definitionReader.loadBeanDefinitions(fileName);
		System.out.println("found "+loadBeanDefinitions+" beans");
		refresh();
	}
	@SuppressWarnings("resource")
	public static void main(String...args){
		 PropertiesFileApplicationContext context = new PropertiesFileApplicationContext("context.properties");
		 context.getBean(Quoter.class).sayQuote();
	}

}

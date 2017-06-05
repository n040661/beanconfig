package beanscope;

import java.awt.Color;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigColorSupplier {
	Random random=new Random();
//	Interface Function<T,R>
//
//	Type Parameters:
//	T - the type of the input to the function
//	R - the type of the result of the function
	@Bean
	public Supplier<Color> colorSupplier(){
		return this::color;
	}
	@Bean
	@Scope("prototype")
	public Color color(){
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	@Bean
	public ColorFrameColorSupplier colorFrameColorSupplier(){
		return new ColorFrameColorSupplier();
	}
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigColorSupplier.class);
		while (true) {
			context.getBean(ColorFrameColorSupplier.class).showOnRandomPlace();
			Thread.sleep(1000);
		}
		
	}

}

package beanscope;

import java.awt.Color;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@ComponentScan(basePackages = "beanscope")
public class Config {
	@Bean
	@Scope("periodical")
	public Color color() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	@Bean
	public ColorFrame colorFrame() {
		return new ColorFrame() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Color getColor() {
				return color();
			}

		};
	}

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		while (true) {
			context.getBean(ColorFrame.class).showOnRandomPlace();
			Thread.sleep(1000);
		}
	}
}

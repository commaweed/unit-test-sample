package tjjenk2.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Creates the spring application context and use an annotation (i.e. @EnableWebMvc) to define it as the bean file
 * known as the dispatcher-servlet.xml file.  You still have to hook it into either a web.xml file of a
 * WebAppInitializer file.
 */
@Configuration
@EnableWebMvc
@ComponentScan("tjjenk2.spring.component")
public class WebAppConfig extends WebMvcConfigurationSupport {

	/**
	 * Add a view resolver for JSP pages.
	 * @return The JSP view resolver.
	 */
	@Bean
	public ViewResolver getInternalResourceViewResolver () {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver;
	}

	/**
	 * Adds a bean name view resolver for JSPs (probably use Thymeleaf templates.
	 * @return The bean name view resolver.
	 */
	@Bean
	public ViewResolver getBeanNameViewResolver ( ) {
		return new BeanNameViewResolver();
	}
}

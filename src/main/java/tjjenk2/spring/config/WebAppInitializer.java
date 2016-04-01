package tjjenk2.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Servlet 3.0 Engine allows us to replace the web.xml with this file.  The code below simulates Spring
 * dispatcher servlet creation in the web.xml.
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@SuppressWarnings("unused")
	private static Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();

		// listen to registration events from our servlet context
		servletContext.addListener(new ContextLoaderListener(context));

		// add the dispatcher servlet and configure it to load on startup and to accept all requests
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
			"DispatcherServlet",
			new DispatcherServlet(context)
		);

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	/**
	 * Return the web application context that is defined by the annotation @EnableWebMvc
	 * @return A reference to the application context (i.e. WebAppConfig)
	 */
	private static AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(WebAppConfig.class.getName());
		return context;
	}
}

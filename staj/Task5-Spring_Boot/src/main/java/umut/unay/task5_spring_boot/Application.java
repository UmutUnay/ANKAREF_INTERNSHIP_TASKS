package umut.unay.task5_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan({ "umut.unay.task5_spring_boot.*" })
public class Application {

	private static Logger log = Logger.getLogger(Application.class.getName());

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry)
			{
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}

}

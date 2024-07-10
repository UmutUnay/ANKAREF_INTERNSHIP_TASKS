package umut.unay.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Task4Application {

	private static Logger log = Logger.getLogger(Task4Application.class.getName());

	public static void main(String[] args)
	{
		log.info("Task4Application is running...");
		SpringApplication.run(Task4Application.class, args);
		log.info("Task4Application is ending...");
	}

}

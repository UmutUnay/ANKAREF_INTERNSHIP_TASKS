package umut.unay.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Task5Application {

	private static Logger log = Logger.getLogger(Task5Application.class.getName());

	public static void main(String[] args)
	{
		SpringApplication.run(Task5Application.class, args);
	}

}

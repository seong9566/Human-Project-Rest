package site.metacoding.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MiniprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniprojectApplication.class, args);
	}

}

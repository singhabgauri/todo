package first_project.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyfirstApplication {

	public static void main(String[] args) {
		try{
		SpringApplication.run(MyfirstApplication.class, args);}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

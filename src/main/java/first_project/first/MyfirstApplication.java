package first_project.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MyfirstApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyfirstApplication.class);

    public static void main(String[] args) {
        try {
            logger.info("Starting Todo Application...");
            SpringApplication app = new SpringApplication(MyfirstApplication.class);
            app.run(args);
            logger.info("Application started successfully!");
        } catch (Exception e) {
            logger.error("Application failed to start: " + e.getMessage(), e);
            System.exit(1);
        }
    }
}

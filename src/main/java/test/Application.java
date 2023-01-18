package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

/**
 * Приложение
 * Как оно создавалось см. здесь: https://www.baeldung.com/rest-with-spring-series
 */
@SpringBootApplication
public class Application {

/*    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

package app;

import app.controllers.PointsController;
import app.controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"app.*"}, scanBasePackageClasses ={UserController.class, PointsController.class})
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class },
//        scanBasePackages = {"app.*"}, scanBasePackageClasses =UserController.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
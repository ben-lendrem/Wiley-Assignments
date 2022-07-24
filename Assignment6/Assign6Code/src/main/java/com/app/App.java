package com.app;

import com.app.controller.GameController;
import com.app.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {


    public static void main(String[] args) {
        RunSpringBoot(args);
    }

    private static void RunSpringBoot(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

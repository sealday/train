package me.theegg.train;

import me.theegg.train.interceptor.UserInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainApplication {

    @Bean
    UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }
}

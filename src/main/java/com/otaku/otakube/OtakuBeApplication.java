package com.otaku.otakube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OtakuBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtakuBeApplication.class, args);
    }

}

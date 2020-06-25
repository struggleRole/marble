package org.marble;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.marble.dao")
public class MarbleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarbleApplication.class, args);
    }

}

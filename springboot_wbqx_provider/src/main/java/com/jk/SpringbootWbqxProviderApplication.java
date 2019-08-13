package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.jk.mapper")
public class SpringbootWbqxProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWbqxProviderApplication.class, args);
    }

}

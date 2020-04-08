package com.nike.douye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author
 * @since 2020/2/11
 *
 */
@EnableTransactionManagement
@MapperScan(basePackages = "com.nike.douye.mapper")
@SpringBootApplication
public class FilmReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmReviewApplication.class, args);
    }

}

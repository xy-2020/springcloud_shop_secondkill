package com.wcq.shop_orders;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.wcq")
@EnableEurekaClient
@MapperScan("com.wcq.dao")
@EnableFeignClients(basePackages = "com.wcq.feign")
public class ShopOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrdersApplication.class, args);
    }
}

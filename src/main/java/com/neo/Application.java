package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient  //该注解能激活Eureka中的DiscoveryClient实现
@EnableCaching//开启缓存
@SpringBootApplication  //等同于@Configuration @EnableAutoConfiguration @ComponentScan三个注解
@ServletComponentScan  //扫描servlet
@MapperScan("com.neo.mapper")//配置需要扫描的Mapper位置
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
} 

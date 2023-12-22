package com.alibaba.nacos.aliyunsamplesstarter;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "config.test", groupId = "DEFAULT", autoRefreshed = true)
public class AliyunSamplesStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AliyunSamplesStarterApplication.class, args);
	}

}

package com.appsdeveloperblog.accountmanagementms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountManagementMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementMsApplication.class, args);
	}

}

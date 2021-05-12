package com.charles.servicemonitor;

import com.mailjet.client.ClientOptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableScheduling
public class ServicemonitorApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServicemonitorApplication.class, args);
	}

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
          .apiInfo(new ApiInfoBuilder()
                .title("Service Monitor")
                .description("API for monitoring Systemd Services")
                .contact(new Contact("Dinneya Charles", "", "dinneyacharles007@gmail.com"))
                .build()
        );
    }
}

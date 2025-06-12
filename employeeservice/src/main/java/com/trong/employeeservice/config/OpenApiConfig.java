package com.trong.employeeservice.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Api Specification - LT FullStack",
                description = "Api documentation for Employee Service",
                version = "1.0",
                contact = @Contact(
                        name = "Trong Ho",
                        email = "trongho.373664@gmail.com"
                ),
                license = @License(
                        name = "Trong License"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9002"
                ),
                @Server(
                        description = "Dev ENV",
                        url = "https://employee-service.dev.com"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://employee-service.prod.com"
                ),
        }
)
public class OpenApiConfig {

}

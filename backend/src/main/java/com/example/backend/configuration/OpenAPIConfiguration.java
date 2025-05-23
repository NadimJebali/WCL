package com.example.backend.configuration;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class OpenAPIConfiguration {
   // http://localhost:8080/swagger-ui/index.html
   
    @Bean
	public OpenAPI customOpenAPI() {

		
		return new OpenAPI()
				.info(new Info().title("Api Project WCL").version("1.0"))				
				.addSecurityItem(new SecurityRequirement().addList("UseSecurityJWT"))
				.components(new Components().addSecuritySchemes("UseSecurityJWT", new SecurityScheme()
						.name("UseSecurityJWT").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
		
	}
}

package br.ada.visitService.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI defaultOpenApiConfig() {
		return new OpenAPI()
                .info(new Info().title("Visit Service")
                        .description("Visit Service Request ")
                        .version("0.0.1"));
    }
	


}

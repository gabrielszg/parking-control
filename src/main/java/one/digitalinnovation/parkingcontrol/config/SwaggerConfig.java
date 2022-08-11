package one.digitalinnovation.parkingcontrol.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Component
public class SwaggerConfig {

	@Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("parking-group")
	              .pathsToMatch("/**")
	              .build();
	  }
	
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI().components(new Components()
	    		.addSecuritySchemes("Login", new SecurityScheme()
	    		.type(SecurityScheme.Type.HTTP)
	    		.scheme("basic")))
	    		.info(new Info().title("Parking REST API")
	  	              .description("Spring Boot REST API for Parking")
		              .version("v1.0.0")
		              .license(new License()
		            		  .name("Apache License Version 2.0")
		            		  .url("https://www.apache.org/licenses/LICENSE-2.0")));
	}
	
}
